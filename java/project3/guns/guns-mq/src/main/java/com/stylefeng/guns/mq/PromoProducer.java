package com.stylefeng.guns.mq;

import com.jiaxingrong.utils.JsonUtils;
import com.jiaxingrong.utils.StringTool;
import com.stylefeng.guns.mq.vo.MQVo;
import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private TransactionMQProducer transactionMQProducer;

    @Value("${mq.nameSevAddr}")
    private String nameSevAddr;

    @Autowired
    MqService mqService;

    @SneakyThrows
    @PostConstruct
    public void init2() {
        transactionMQProducer = new TransactionMQProducer("transaction1");
        transactionMQProducer.setNamesrvAddr(nameSevAddr);
        transactionMQProducer.start();
        transactionMQProducer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object arg) {
                Integer status = (Integer) arg;
                byte[] body = message.getBody();
                String bodyStr = new String(body);
                MQVo mqVo = JsonUtils.convertToObject(bodyStr, MQVo.class);
                Integer amount = mqVo.getAmount();
                Integer promoId = mqVo.getPromoId();
                Boolean aBoolean = null;
                if (status == 1) {
                    try {
                        aBoolean = mqService.sendRedisStock(promoId, amount);
                    } catch (Exception e) {
                        status = -1 ;
                        return LocalTransactionState.ROLLBACK_MESSAGE;
                    }
                }
                if (aBoolean == null || aBoolean == false) {
                    status = -1 ; //如果失败 消息为-1
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
                status = 2 ; //如果成功 消息为2
                return LocalTransactionState.COMMIT_MESSAGE;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                byte[] body = messageExt.getBody();
                String statusStr = new String(body);
                if (!StringTool.isNumber(statusStr)) {
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
                Integer status = Integer.parseInt(statusStr);
                if (status == -1) {
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
                if (status == 2){
                    return LocalTransactionState.COMMIT_MESSAGE;
                }
                return LocalTransactionState.UNKNOW;
            }
        });
    }


    @SneakyThrows
    @PostConstruct
    public void init() {
        defaultMQProducer = new DefaultMQProducer("producer1");
        defaultMQProducer.setNamesrvAddr(nameSevAddr);
        defaultMQProducer.start();
    }

    @SneakyThrows
    public Boolean sendRedisStock(Integer promoId, Integer amount) {
        MQVo mqVo = new MQVo(promoId, amount);
        String toJson = JsonUtils.convertToJson(mqVo);
        Message message = new Message("redis", toJson.getBytes(Charset.forName("utf-8")));
        SendResult send = defaultMQProducer.send(message);
        SendStatus sendStatus = send.getSendStatus();
        if (SendStatus.SEND_OK.equals(sendStatus)) {
            return true;
        }
        return false;
    }

    @SneakyThrows
    public Boolean changeOrderStatus(String orderId) {


        Message message = new Message("order", orderId.getBytes(Charset.forName("utf-8")));
        message.setDelayTimeLevel(16);
        SendResult send = defaultMQProducer.send(message);
        SendStatus sendStatus = send.getSendStatus();
        if (SendStatus.SEND_OK.equals(sendStatus)) {
            return true;
        }
        return false;
    }

    @SneakyThrows
    public Boolean deleteAliPayPic(String picAddress) {

        Message message = new Message("deletePic", picAddress.getBytes(Charset.forName("utf-8")));
        message.setDelayTimeLevel(12);
        SendResult send = defaultMQProducer.send(message);
        SendStatus sendStatus = send.getSendStatus();
        if (SendStatus.SEND_OK.equals(sendStatus)) {
            return true;
        }
        return false;
    }

    @SneakyThrows
    public Boolean sendMysqlStock(Integer promoId, Integer amount, Integer userId) {
        MQVo mqVo = new MQVo(promoId, userId, amount);
        String toJson = JsonUtils.convertToJson(mqVo);
        Message message = new Message("mysql", toJson.getBytes(Charset.forName("utf-8")));
        SendResult send = defaultMQProducer.send(message);
        SendStatus sendStatus = send.getSendStatus();
        if (SendStatus.SEND_OK.equals(sendStatus)) {
            return true;
        }
        return false;
    }

    @SneakyThrows
    public Boolean sendRedisStockTransaction(Integer promoId, Integer amount) {
        MQVo mqVo = new MQVo(promoId, amount);
        String toJson = JsonUtils.convertToJson(mqVo);
        Message message = new Message("redis", toJson.getBytes(Charset.forName("utf-8")));
        SendResult send = defaultMQProducer.send(message);
        SendStatus sendStatus = send.getSendStatus();
        if (SendStatus.SEND_OK.equals(sendStatus)) {
            return true;
        }
        return false;
    }

    @SneakyThrows
    public Boolean sendMysqlStockTransaction(Integer promoId, Integer amount, Integer userId) {
        MQVo mqVo = new MQVo(promoId, userId, amount);
        String toJson = JsonUtils.convertToJson(mqVo);
        Message message = new Message("mysql", toJson.getBytes(Charset.forName("utf-8")));
        SendResult send = transactionMQProducer.sendMessageInTransaction(message, 1);
        SendStatus sendStatus = send.getSendStatus();
        if (SendStatus.SEND_OK.equals(sendStatus)) {
            return true;
        }
        return false;
    }

}
