package com.stylefeng.guns.rest.common.persistence.pay;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.alipay.AliPayService;
import com.stylefeng.guns.order.OrderService;
import com.stylefeng.guns.pay.PayService;
import com.stylefeng.guns.pay.vo.OrderPayCinemaVo;
import com.stylefeng.guns.pay.vo.PayRespVo;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-11 21:50
 */

@Component
@Service(interfaceClass = PayService.class)
public class PayServiceImpl implements PayService {

    @Reference(interfaceClass = AliPayService.class,retries = 1)
    private AliPayService aliPayService;

    @Reference(interfaceClass = OrderService.class,retries = 1)
    private OrderService orderService;

    @Override
    public Map<String, String> getPayInfo(String orderId) {

        OrderPayCinemaVo payCinemaVo = orderService.queryCinemaDetailByOrderId(orderId);
        String payQRCode = aliPayService.getPayQRCode(orderId, payCinemaVo.getCinemaName(), payCinemaVo.getPrice(), String.valueOf(payCinemaVo.getCinemaId()));

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("QRCodeAddress",payQRCode);
        hashMap.put("orderId",orderId);
        return hashMap;
    }

    @Override
    public PayRespVo getPayResult(String orderId) {
        PayRespVo respVo = new PayRespVo();
        Integer payResult = aliPayService.getPayResult(orderId);
        respVo.setOrderId(orderId);
        respVo.setOrderStatus(payResult);
        if (payResult==1){
            respVo.setOrderMsg("支付成功");
            updateOrderStatus(orderId,payResult);
        }else if (payResult==2){
            respVo.setOrderMsg("支付失败");
            updateOrderStatus(orderId,payResult);
        }else {
            respVo.setOrderMsg("待支付");
        }
        return respVo;
    }

    @Override
    public Integer updateOrderStatus(String orderId, Integer payResult) {
        return orderService.updateOrderByorderId(orderId,payResult);
    }


}
