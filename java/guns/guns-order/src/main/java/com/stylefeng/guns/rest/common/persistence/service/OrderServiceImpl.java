package com.stylefeng.guns.rest.common.persistence.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jiaxingrong.utils.CollectionUtils;
import com.jiaxingrong.utils.FileUtils;
import com.jiaxingrong.utils.JsonUtils;
import com.jiaxingrong.utils.StringTool;
import com.stylefeng.guns.cinema.CinemaService;
import com.stylefeng.guns.json.Seat;
import com.stylefeng.guns.json.SingleEntity;
import com.stylefeng.guns.order.OrderService;
import com.stylefeng.guns.order.vo.OrderField;
import com.stylefeng.guns.order.vo.OrderReqVo;
import com.stylefeng.guns.order.vo.OrderRespVo;
import com.stylefeng.guns.pay.vo.OrderPayCinemaVo;
import com.stylefeng.guns.rest.common.persistence.dao.MoocOrderTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocOrderT;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-11 10:51
 */
@Component
@Service(interfaceClass = OrderService.class)
public class OrderServiceImpl implements OrderService {

    @Value("D:\\RedisProject\\guns\\guns-order\\src\\main\\resources\\")
    private String seatsJsonFilePah;

    @Autowired
    private MoocOrderTMapper orderTMapper;

    @Reference(interfaceClass = CinemaService.class)
    private CinemaService cinemaService;

    @Override
    public OrderRespVo buyTickets(OrderReqVo orderReqVo, String seat_address, Integer userId, OrderField orderField, String filmName,String cinemaName) {
        OrderRespVo respVo = new OrderRespVo();


        List<Integer> soldSeats = orderReqVo.getSoldSeats();
        Integer fieldId = orderReqVo.getFieldId();
        Boolean trueSeats = isTrueSeats(fieldId, soldSeats, seat_address);
        if (!trueSeats) return respVo;

        Boolean soldSeats1 = isSoldSeats(fieldId, soldSeats);
        if (!soldSeats1) return respVo;
        String orderId = getOrderId(userId);
        MoocOrderT orderT = new MoocOrderT();
        orderT.setUuid(orderId);
        orderT.setCinemaId(orderField.getCinema_id());
        orderT.setFieldId(orderField.getUUID());
        orderT.setFilmId(orderField.getFilm_id());
        orderT.setSeatsIds(orderReqVo.getSoldSeats().toString());
        String seatsName = getSeatsName(orderReqVo.getSoldSeats(), seat_address);
        orderT.setSeatsName(seatsName);
        orderT.setFilmPrice(Double.valueOf(orderField.getPrice()));
        orderT.setOrderPrice((double) (orderReqVo.getSoldSeats().size() * orderField.getPrice()));
        orderT.setOrderTime(new Date());
        orderT.setOrderUser(userId);
        orderT.setOrderStatus(0);

        Integer insertAllColumn = orderTMapper.insertAllColumn(orderT);
        if (insertAllColumn != 1) return respVo;

        respVo.setOrderId(orderId);
        respVo.setFilmName(filmName);
        respVo.setFieldTime(StringTool.getToDay() + " " + orderField.getBegin_time());
        respVo.setCinemaName(cinemaName);
        respVo.setSeatsName(seatsName);
        respVo.setOrderPrice(String.valueOf(orderT.getOrderPrice()));
        respVo.setOrderTimestamp(String.valueOf(System.currentTimeMillis()));
        return respVo;
    }

    private String getSeatsName(List<Integer> soldSeats, String seat_address) {
        String s = FileUtils.readJsonData(seatsJsonFilePah + seat_address);
        Seat seat = JsonUtils.convertToObject(s, Seat.class);

        StringBuffer sb = new StringBuffer();
        for (Integer soldSeat : soldSeats) {
            List<List<Seat.SingleEntity>> single = seat.getSingle();
            if (!CollectionUtils.isEmpty(single)){
                for (List<Seat.SingleEntity> singleEntities : single) {
                    for (Seat.SingleEntity entity : singleEntities) {
                        if (soldSeat.equals(entity.getSeatId())){
                            sb.append(entity.getRow()).append("排").append(entity.getColumn()).append("座").append(" ");
                        }
                    }
                }
            }
            List<List<Seat.SingleEntity>> couple = seat.getCouple();
            if (!CollectionUtils.isEmpty(couple)){
                for (List<Seat.SingleEntity> entities : couple) {
                    for (Seat.SingleEntity entity : entities) {
                        if (soldSeat.equals(entity.getSeatId())){
                            sb.append(entity.getRow()).append("排").append(entity.getColumn()).append("座").append(" ");
                        }
                    }
                }
            }
        }

        return sb.toString().trim();
    }

    @Override
    public Boolean isTrueSeats(Integer filedId, List<Integer> soldSeats, String seat_address) {
        String s = FileUtils.readJsonData(seatsJsonFilePah + seat_address);
        Seat seat = JsonUtils.convertToObject(s, Seat.class);
        String ids = seat.getIds();
        List<Integer> list = StringTool.stringToIntList(ids);
        for (Integer soldSeat : soldSeats) {
            if (!list.contains(soldSeat)) return false;
        }
        return true;
    }

    @Override
    public Boolean isSoldSeats(Integer filedId, List<Integer> soldSeats) {
        EntityWrapper<MoocOrderT> wrapper = new EntityWrapper<>();
        wrapper.eq("field_id",filedId);
        wrapper.in("order_status","1,2");
        List<MoocOrderT> orderTS = orderTMapper.selectList(wrapper);
        List<String> list = new ArrayList<>();
        for (MoocOrderT orderT : orderTS) {
            list.add(orderT.getSeatsIds());
        }
        if (!CollectionUtils.isEmpty(list)) {
            ArrayList<Integer> integers = new ArrayList<>();
            for (String s : list) {
                ArrayList<Integer> list1 = StringTool.stringToIntList(s);
                for (Integer integer : list1) {
                    integers.add(integer);
                }
            }
            for (Integer soldSeat : soldSeats) {
                if (integers.contains(soldSeat)) return false;
            }
        }
        return true;
    }

    @Override
    public List<OrderRespVo> getOrderInfo(Integer userId, Integer nowPage, Integer pageSize) {
        EntityWrapper<MoocOrderT> wrapper = new EntityWrapper<>();
        wrapper.eq("order_user",userId);
        RowBounds rowBounds = new RowBounds(nowPage - 1, pageSize);
        List<MoocOrderT> orderTS = orderTMapper.selectPage(rowBounds, wrapper);
        List<OrderRespVo> respVos = new ArrayList<>();
        for (MoocOrderT orderT : orderTS) {
            OrderRespVo respVo = new OrderRespVo();
            respVo.setOrderId(orderT.getUuid());
            String cinemaName = cinemaService.queryCinemaNameByCinemaId(orderT.getCinemaId());
            OrderField orderField = cinemaService.queryFieldHallNameByFiledId(orderT.getFieldId());
            String filmName = cinemaService.queryFilmNameByFilmName(orderT.getFilmId());
            respVo.setFilmName(filmName);
            respVo.setFieldTime(StringTool.getToDay() + " " + orderField.getBegin_time());
            respVo.setCinemaName(cinemaName);
            respVo.setSeatsName(orderT.getSeatsName());
            respVo.setOrderPrice(String.valueOf(orderT.getOrderPrice()));
            Integer status = orderT.getOrderStatus();
            //0-待支付,1-已支付,2-已关闭
            if (status==0){
                respVo.setOrderStatus("待支付");
            }else if (status==1){
                respVo.setOrderStatus("已支付");
            }else {
                respVo.setOrderStatus("已关闭");
            }
            respVos.add(respVo);
        }
        return respVos;
    }

    @Override
    public OrderPayCinemaVo queryCinemaDetailByOrderId(String orderId) {
        MoocOrderT moocOrderT = orderTMapper.selectOrderByOrderId(orderId);
        OrderPayCinemaVo payCinemaVo = new OrderPayCinemaVo();

        if (moocOrderT!=null){
            payCinemaVo.setCinemaId(moocOrderT.getCinemaId());
            String cinemaName = cinemaService.queryCinemaNameByCinemaId(moocOrderT.getCinemaId());
            payCinemaVo.setCinemaName(cinemaName);
            payCinemaVo.setPrice(String.valueOf(moocOrderT.getOrderPrice()));
        }
        return payCinemaVo;
    }


    private String getOrderId(Integer userId) {
        StringBuffer stringBuffer = new StringBuffer();
        Date date = new Date();
        long time = date.getTime();
        String s = StringTool.hashRSA(String.valueOf(time));

        stringBuffer.append(s.substring(s.length() - 6, s.length()));

        String uuidNoCtrl = StringTool.getUUIDNoCtrl();
        stringBuffer.append(uuidNoCtrl.substring(uuidNoCtrl.length() - 6, uuidNoCtrl.length()));

        String hashCode = StringTool.hashRSA(String.valueOf(userId));
        stringBuffer.append(s.substring(hashCode.length() - 6, hashCode.length()));
        return stringBuffer.toString();
    }
}
