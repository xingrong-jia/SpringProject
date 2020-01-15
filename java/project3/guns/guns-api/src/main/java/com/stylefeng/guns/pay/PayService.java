package com.stylefeng.guns.pay;

import com.stylefeng.guns.pay.vo.PayRespVo;

import java.util.Map;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-11 21:50
 */
public interface PayService {
    Map<String, String> getPayInfo(String orderId);

    PayRespVo getPayResult(String orderId);

    Integer updateOrderStatus(String orderId,Integer payResult);
}
