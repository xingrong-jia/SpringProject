package com.stylefeng.guns.mq;

import com.jiaxingrong.utils.JsonUtils;
import com.stylefeng.guns.mq.vo.MQVo;
import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-14 15:46
 */
@Component
public class PromoProducer {

    private DefaultMQProducer defaultMQProducer;


    @SneakyThrows
    @PostConstruct
    public void init() {
        defaultMQProducer = new DefaultMQProducer("producer1");
        defaultMQProducer.setNamesrvAddr("192.168.5.31:9876");
        defaultMQProducer.start();
    }

    @SneakyThrows
    public Boolean sendRedisStock(Integer promoId,Integer amount) {
        MQVo mqVo = new MQVo(promoId,amount);
        String toJson = JsonUtils.convertToJson(mqVo);
        Message message = new Message("redis",toJson.getBytes(Charset.forName("utf-8")));
        SendResult send = defaultMQProducer.send(message);
        SendStatus sendStatus = send.getSendStatus();
        if (SendStatus.SEND_OK.equals(sendStatus)){
            return true;
        }
        return false;
    }

    @SneakyThrows
    public Boolean sendMysqlStock(Integer promoId,Integer amount,Integer userId) {
        MQVo mqVo = new MQVo(promoId,userId,amount);
        String toJson = JsonUtils.convertToJson(mqVo);
        Message message = new Message("mysql",toJson.getBytes(Charset.forName("utf-8")));
        SendResult send = defaultMQProducer.send(message);
        SendStatus sendStatus = send.getSendStatus();
        if (SendStatus.SEND_OK.equals(sendStatus)){
            return true;
        }
        return false;
    }

}
