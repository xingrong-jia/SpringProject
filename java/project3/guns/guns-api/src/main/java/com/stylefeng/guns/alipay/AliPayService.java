package com.stylefeng.guns.alipay;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-11 19:13
 */
public interface AliPayService {

    String getPayQRCode(String orderId,String cinemaName,String price,String cinemaId);

    Integer getPayResult(String orderId);
}
