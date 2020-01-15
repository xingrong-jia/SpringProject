package com.stylefeng.guns.rest.expection;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.Charset;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-14 17:26
 */
public class MqProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        DefaultMQProducer mqProducer = new DefaultMQProducer("producer_group");
        mqProducer.setNamesrvAddr("192.168.5.31:9876");

        mqProducer.start();

        Message message = new Message("stock", "hello".getBytes(Charset.forName("utf-8")));

        SendResult sendResult = mqProducer.send(message);

        System.out.println("发送消息成功...");
    }
}
