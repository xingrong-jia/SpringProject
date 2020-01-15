package com.stylefeng.guns.rest.expection;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-14 17:16
 */
public class MqConsumer {

    public static void main(String[] args) {
        DefaultMQPushConsumer comsumer = new DefaultMQPushConsumer("comsumer_group");
        comsumer.setNamesrvAddr("192.168.5.31:9876");
        try {
            comsumer.subscribe("stock","*");
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        comsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                MessageExt messageExt = list.get(0);
                String bodyStr = new String(messageExt.getBody());
                System.out.println("收到消息：" + bodyStr);

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        try {
            comsumer.start();
            System.out.println("消息消费者启动成功...");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

}
