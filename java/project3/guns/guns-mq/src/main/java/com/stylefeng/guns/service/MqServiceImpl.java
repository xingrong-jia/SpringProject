package com.stylefeng.guns.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.mq.MqService;
import com.stylefeng.guns.mq.PromoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-14 20:18
 */
@Component
@Service(interfaceClass = MqService.class)
public class MqServiceImpl implements MqService {

    @Autowired
    private PromoProducer promoProducer;

    @Override
    public Boolean sendRedisStock(Integer promoId, Integer amount) {
        return promoProducer.sendRedisStock(promoId,amount);
    }

    @Override
    public Boolean sendMysqlStock(Integer promoId, Integer amount, Integer userId) {
        Boolean mysqlStock = promoProducer.sendMysqlStock(promoId, amount, userId);
        return mysqlStock;
    }

    @Override
    public Boolean sendMysqlStockTransaction(Integer promoId, Integer amount, Integer userId) {
        Boolean mysqlStock = promoProducer.sendMysqlStockTransaction(promoId, amount, userId);
        return mysqlStock;
    }
}
