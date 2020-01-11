package com.stylefeng.guns.order;

import com.stylefeng.guns.order.vo.OrderField;
import com.stylefeng.guns.order.vo.OrderReqVo;
import com.stylefeng.guns.order.vo.OrderRespVo;
import com.stylefeng.guns.pay.vo.OrderPayCinemaVo;

import java.util.List;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-11 10:40
 */
public interface OrderService {
    OrderRespVo buyTickets(OrderReqVo orderReqVo, String seat_address, Integer userId, OrderField orderField,String filmName,String cinemaName);

    Boolean isTrueSeats (Integer filedId, List<Integer> soldSeats,String seat_address);

    Boolean isSoldSeats (Integer filedId,List<Integer> soldSeats);

    List<OrderRespVo> getOrderInfo(Integer userId, Integer nowPage, Integer pageSize);

    OrderPayCinemaVo queryCinemaDetailByOrderId(String orderId);
}
