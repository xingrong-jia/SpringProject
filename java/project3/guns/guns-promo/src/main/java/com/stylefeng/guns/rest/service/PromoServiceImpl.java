package com.stylefeng.guns.rest.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jiaxingrong.utils.CollectionUtils;
import com.jiaxingrong.utils.DateUtils;
import com.jiaxingrong.utils.StringTool;
import com.stylefeng.guns.alipay.AliPayService;
import com.stylefeng.guns.cinema.CinemaService;
import com.stylefeng.guns.cinema.vo.CinemasReqVo;
import com.stylefeng.guns.order.vo.OrderRespVo;
import com.stylefeng.guns.promo.PromoService;
import com.stylefeng.guns.promo.vo.PromoReqVo;
import com.stylefeng.guns.promo.vo.PromoRespVo;
import com.stylefeng.guns.rest.common.persistence.dao.MtimePromoMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimePromoOrderMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MtimePromoStockMapper;
import com.stylefeng.guns.rest.common.persistence.model.MtimePromo;
import com.stylefeng.guns.rest.common.persistence.model.MtimePromoOrder;
import com.stylefeng.guns.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-13 8:56
 */
@Slf4j
@Component
@Service(interfaceClass = PromoService.class)
public class PromoServiceImpl implements PromoService {

    @Autowired
    private MtimePromoMapper promoMapper;

    @Autowired
    private MtimePromoStockMapper stockMapper;

    @Autowired
    private MtimePromoOrderMapper orderMapper;


    @Reference(interfaceClass = CinemaService.class, retries = 1)
    private CinemaService cinemaService;

    @Reference(interfaceClass = AliPayService.class, retries = 1)
    private AliPayService aliPayService;

    @Reference(interfaceClass = UserService.class, retries = 1)
    private UserService userService;

    @Value("proms")
    private String proms;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("genToken")
    private String generateToken;

    @Value("promoCount")
    private String promoCount;

    private Integer suffix = 1000000;

    @Value("JXR")
    private String prefix;

    private Boolean isSyncRedis;


    @Override
    public List<PromoRespVo> getPromos(CinemasReqVo reqVo) {

        Wrapper<MtimePromo> promoWrapper = new EntityWrapper<>();
        HashMap<String, Object> map = new HashMap<>();
        if (reqVo.getCinemaId() != 0) {
            map.put("cinema_id", reqVo.getCinemaId());
        }
        map.put("status", 1);
        promoWrapper.allEq(map);
        List<MtimePromo> promoRespVos = promoMapper.selectList(promoWrapper);
        List<PromoRespVo> respVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(promoRespVos)) {
            for (MtimePromo mtimePromo : promoRespVos) {

                Integer promoStock = stockMapper.selectStockByPromoId(mtimePromo.getUuid());
                PromoRespVo respVo = cinemaService.queryPromoCinemaVoBycinemaId(mtimePromo.getCinemaId());

                respVo.setCinemaId(reqVo.getCinemaId());

                respVo.setDescription(mtimePromo.getDescription());
                respVo.setEndTime(mtimePromo.getEndTime());
                respVo.setPrice(mtimePromo.getPrice().intValue());
                respVo.setStartTime(mtimePromo.getStartTime());
                respVo.setStatus(mtimePromo.getStatus());
                Integer stock = (Integer) redisTemplate.opsForValue().get(proms + mtimePromo.getUuid());
                if (stock!=null) {
                    respVo.setStock(stock);
                }else {
                    respVo.setStock(promoStock);
                }
                respVo.setUuid(mtimePromo.getUuid());
                respVos.add(respVo);
            }
        }
        return respVos;
    }

    @Override
    public Integer publishPromoStock(Integer cinemaId) {
        if (isSyncRedis != null && isSyncRedis) return 0;
        CinemasReqVo cinemasReqVo = new CinemasReqVo();
        if (cinemaId != null) {
            cinemasReqVo.setCinemaId(cinemaId);
        }
        List<PromoRespVo> promoRespVos = getPromos(cinemasReqVo);
        if (!CollectionUtils.isEmpty(promoRespVos)) {
            for (PromoRespVo promo : promoRespVos) {
                redisTemplate.opsForValue().set(proms + promo.getUuid(), promo.getStock());
                Integer stock = (Integer) redisTemplate.opsForValue().get(proms + promo.getUuid());
                log.info(String.valueOf(stock));
                int v = (int) (promo.getStock() * 1.5);
                redisTemplate.opsForValue().set(promoCount+promo.getUuid(),v);
            }
        }
        isSyncRedis = true;
        return 0;
    }

    @Override
    public String generateToken(Integer promoId, Integer userId) {
        String token = "";
        MtimePromo promo = promoMapper.selectById(promoId);
        if (promo.getStatus() == 1) {
            Integer integer = stockMapper.selectStockByPromoId(promoId);
            String promoCountId = String.valueOf(redisTemplate.opsForValue().get(promoCount + promoId));
            if (!StringTool.isNumber(promoCountId)||Integer.parseInt(promoCountId) <= 0) return token;
            Object o = redisTemplate.opsForValue().get(generateToken + userId + promoId);
            if (o != null) return token;
            if (integer > 0) {
                token = getToken(promoId, userId);
                redisTemplate.opsForValue().set(generateToken + userId + promoId, token);
                redisTemplate.opsForValue().increment(promoCount + promoId,-1);
            }
        }
        return token;
    }

    /**
     * @param reqVo
     * @return -4表示购买数量大于10
     * -3表示商品目前所剩库存小于用户购买数量
     * -2表示该表示活动不存在或商品已经售空
     * -1表示用户没有秒杀令牌或秒杀令牌和顾客之前申请的秒杀令牌不一致
     * 1表示下单成功
     */
    @Transactional
    @Override
    public Integer createOrder(PromoReqVo reqVo) {
        Integer status = null;
        Integer promoId = reqVo.getPromoId();
        Integer amount = reqVo.getAmount();
        if (amount <= 0 || amount > 10) return -4;

        Integer stock = (Integer) redisTemplate.opsForValue().get(proms + promoId);
        if (stock == null) return -2;

        if (stock < amount) return -3;
        Integer userId = reqVo.getUserId();
        String token = (String) redisTemplate.opsForValue().get(generateToken + userId + promoId);
        if (!StringTool.isNotNull(token) || !token.equals(reqVo.getPromoToken())) {
            return -1;
        }
        redisTemplate.delete(generateToken + userId + promoId);
        //redisTemplate.boundHashOps(generateToken).delete(userId);

        return 1;

    }


    private String getToken(Integer promoId, Integer userId) {
        StringBuffer stringBuffer = new StringBuffer();
        String hashRSA2 = StringTool.hashRSA(String.valueOf(userId));
        stringBuffer.append(hashRSA2.substring(hashRSA2.length() - 8));
        String hashRSA = StringTool.hashRSA(String.valueOf(promoId));
        stringBuffer.append(hashRSA.substring(hashRSA.length() - 8));
        long time = new Date().getTime();
        String hashRSA1 = StringTool.hashRSA(String.valueOf(time));
        stringBuffer.append(hashRSA1.substring(hashRSA1.length() - 8));
        String noCtrl = StringTool.getUUIDNoCtrl();
        stringBuffer.append(noCtrl.substring(noCtrl.length() - 8));
        return stringBuffer.toString();
    }

    public Integer createPromoOrder(Integer userId, Integer promoId, Integer amount) {
        MtimePromoOrder promoOrder = new MtimePromoOrder();
        MtimePromo promo = promoMapper.selectById(promoId);
        promoOrder.setUserId(userId);
        promoOrder.setUuid(getOrderId(userId));
        promoOrder.setCinemaId(promo.getCinemaId());
        String exchangeCode = getExchangeCode(userId, promoId, new Date());
        promoOrder.setExchangeCode(exchangeCode);
        promoOrder.setAmount(amount);
        BigDecimal decimal = BigDecimal.valueOf(amount * promo.getPrice().intValue());
        promoOrder.setPrice(decimal);
        Date date = new Date();
        promoOrder.setStartTime(date);
        promoOrder.setCreateTime(date);
        promoOrder.setEndTime(DateUtils.getNDayDate(7, date));

        Integer insert = orderMapper.insertPromoOrder(promoOrder);

        String userPhone = userService.queryUserPhoneByUserId(userId);

        aliPayService.SendMessage(userPhone,exchangeCode,getOrderId(userId));
        return insert;
    }

    private String getOrderId(Integer userId) {
        StringBuffer stringBuffer = new StringBuffer();
        Date date = new Date();
        long time = date.getTime();
        String s = StringTool.hashRSA(String.valueOf(time));

        stringBuffer.append(s.substring(s.length() - 6));

        String uuidNoCtrl = StringTool.getUUIDNoCtrl();
        stringBuffer.append(uuidNoCtrl.substring(uuidNoCtrl.length() - 6));

        String hashCode = StringTool.hashRSA(String.valueOf(userId));
        String substring = hashCode.substring(hashCode.length() - 6);
        stringBuffer.append(substring);
        return stringBuffer.toString();
    }

    @Override
    public Integer updatePromoStock(Integer promoId, Integer amount) {
        Integer update = stockMapper.updateStockByPromoId(promoId, amount);

        return update;
    }

    @Override
    public List<OrderRespVo> getOrderInfo(Integer userId, Integer nowPage, Integer pageSize) {
        Wrapper<MtimePromoOrder> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId);
        RowBounds rowBounds = new RowBounds(nowPage - 1, pageSize);
        List<MtimePromoOrder> orderTS = orderMapper.selectPage(rowBounds, wrapper);
        List<OrderRespVo> respVos = new ArrayList<>();
        for (MtimePromoOrder orderT : orderTS) {
            OrderRespVo respVo = new OrderRespVo();
            respVo.setOrderId(orderT.getUuid());
            String cinemaName = cinemaService.queryCinemaNameByCinemaId(orderT.getCinemaId());
            //OrderField orderField = cinemaService.queryFieldHallNameByFiledId(orderT.getFieldId());
            //String filmName = cinemaService.queryFilmNameByFilmName(orderT.getFilmId());
            respVo.setFilmName("兑换码：" + orderT.getExchangeCode());
            respVo.setStartTime("兑换开始时间：" + StringTool.date2StringBeHmS(orderT.getStartTime()));
            respVo.setEndTime("------兑换截止时间：" + StringTool.date2StringBeHmS(orderT.getEndTime()));
            respVo.setCinemaName(cinemaName);
            respVo.setSeatsName("");
            respVo.setOrderPrice(String.valueOf(orderT.getPrice()));
            respVo.setOrderTimestamp(StringTool.date2StringBeHmS(orderT.getCreateTime()));

            respVo.setOrderStatus("已支付");
            respVos.add(respVo);
        }
        return respVos;
    }

    private String getExchangeCode(Integer userId, Integer promoId, Date date) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(prefix);//3位
        String ctrl = StringTool.date2StringBeHmSNotCtrl(new Date());
        if ("000000".equals(ctrl.substring(7))) {
            suffix = 1000000;
        }
        buffer.append(ctrl);
        String hashRSA = StringTool.hashRSA(String.valueOf(userId));
        String hashRSA1 = StringTool.hashRSA(String.valueOf(promoId));
        buffer.append(hashRSA.substring(hashRSA.length() - 6));
        buffer.append(hashRSA.substring(hashRSA1.length() - 6));

        buffer.append(suffix++);
        return buffer.toString();
    }
}
