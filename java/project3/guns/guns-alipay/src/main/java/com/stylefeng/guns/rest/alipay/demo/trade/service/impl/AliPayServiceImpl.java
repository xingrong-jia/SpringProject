package com.stylefeng.guns.rest.alipay.demo.trade.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.stylefeng.guns.alipay.AliPayService;
import com.stylefeng.guns.rest.alipay.demo.trade.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-11 19:13
 */
@Component
@Service(interfaceClass = AliPayService.class,retries = 1)
public class AliPayServiceImpl implements AliPayService {

    private Main main = new Main();

    @Autowired
    AlipayClient alipayClient;


    @Override
    public String getPayQRCode(String orderId, String cinemaName, String price, String cinemaId) {
        String qrCodePath = main.getPayQRCode(orderId, cinemaName, price, cinemaId);
        return qrCodePath;
    }

    @Override
    public Integer getPayResult(String orderId){
        String payResult = queryPayResult(orderId);
        Integer integer = null;
        if ("TRADE_SUCCESS".equals(payResult)){
            integer = 1;
        }else if ("TRADE_CLOSED".equals(payResult)||"TRADE_FINISHED".equals(payResult)){
            integer = 2;
        }else {
            integer = 0;
        }
        return integer;
    }

    private String queryPayResult(String orderId) {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{"+"\"out_trade_no\":\""+orderId+"\"}");
        System.out.println(request.getBizContent());
        AlipayTradeQueryResponse response = null;
        String tradeStatus = null;
        try {
            response = alipayClient.execute(request);
            tradeStatus = response.getTradeStatus();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return  tradeStatus;
    }
}
