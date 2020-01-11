package com.stylefeng.guns.rest.alipay.demo.trade.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.alipay.AliPayService;
import com.stylefeng.guns.rest.alipay.demo.trade.Main;
import org.springframework.stereotype.Component;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-11 19:13
 */
@Component
@Service(interfaceClass = AliPayService.class)
public class AliPayServiceImpl implements AliPayService {

    private Main main = new Main();


    @Override
    public String getPayQRCode(String orderId, String cinemaName, String price, String cinemaId) {
        String qrCodePath = main.getPayQRCode(orderId, cinemaName, price, cinemaId);
        return qrCodePath;
    }
}
