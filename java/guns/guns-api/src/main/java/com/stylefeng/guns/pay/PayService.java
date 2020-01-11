package com.stylefeng.guns.pay;

import java.util.Map;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-11 21:50
 */
public interface PayService {
    Map<String, String> getPayInfo(String orderId);
}
