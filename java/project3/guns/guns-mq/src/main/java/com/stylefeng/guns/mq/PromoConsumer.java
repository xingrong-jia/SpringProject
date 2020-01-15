package com.stylefeng.guns.mq;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jiaxingrong.utils.JsonUtils;
import com.stylefeng.guns.mq.vo.MQVo;
import com.stylefeng.guns.promo.PromoService;
import lombok.SneakyThrows;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-14 16:02
 */
@Component
public class PromoConsumer {

    private DefaultMQPushConsumer mysqlConsumer;

    private DefaultMQPushConsumer redisConsumer;

    @Autowired
    private RedisTemplate redisTemplate;


    @Reference(interfaceClass = PromoService.class,retries = 1)
    private PromoService promoService;


    @PostConstruct
    public void init1() throws MQClientException {
        redisConsumer = new DefaultMQPushConsumer("consumer1");
        redisConsumer.setNamesrvAddr("192.168.5.31:9876");
        redisConsumer.subscribe("redis", "*");
        redisConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                Message message = list.get(0);
                String body = new String(message.getBody());
                MQVo mqVo = JsonUtils.convertToObject(body, MQVo.class);
                Integer promoId = mqVo.getPromoId();
                int i = mqVo.getAmount() * -1;
                Integer amount = i;

                System.out.println("redis消费者--->"+body);
                redisTemplate.opsForValue().increment("proms"+promoId,amount);

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        redisConsumer.start();
        System.out.println("redis消费者启动成功！");
    }


    @PostConstruct
    public void init2() throws MQClientException {
        mysqlConsumer = new DefaultMQPushConsumer("consumer2");
        mysqlConsumer.setNamesrvAddr("192.168.5.31:9876");
        mysqlConsumer.subscribe("mysql", "*");
        mysqlConsumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                Message message = list.get(0);

                //Byte[] bytes = new Byte[]{};
                //String s = bytes.toString();
                String body = new String(message.getBody());
                MQVo mqVo = JsonUtils.convertToObject(body, MQVo.class);
                Integer promoId = mqVo.getPromoId();
                Integer amount = mqVo.getAmount() ;
                Integer userId = mqVo.getUserId();
                System.out.println("mysql消费者--->"+body);
                Integer integer = promoService.createPromoOrder(userId, promoId, amount);
                amount = amount * -1;
                int update = promoService.updatePromoStock(promoId,amount);
                if (integer!=1){
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }else {
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            }
        });
        mysqlConsumer.start();
        System.out.println("mysql消费者启动成功！");
    }

}
