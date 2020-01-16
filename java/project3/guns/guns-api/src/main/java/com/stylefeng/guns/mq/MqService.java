package com.stylefeng.guns.mq;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-14 20:19
 */
public interface MqService {
    Boolean sendRedisStock(Integer promoId, Integer amount);

    Boolean sendMysqlStock(Integer promoId, Integer amount, Integer userId);

    Boolean sendMysqlStockTransaction(Integer promoId, Integer amount, Integer userId);
}
