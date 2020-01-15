package com.stylefeng.guns.rest.common.persistence.control;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jiaxingrong.utils.StringTool;
import com.stylefeng.guns.pay.PayService;
import com.stylefeng.guns.pay.vo.PayRespVo;
import com.stylefeng.guns.rest.model.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-11 21:51
 */
@RestController
@RequestMapping("order")
public class PayController {

    @Reference(interfaceClass = PayService.class,retries = 1)
    private PayService payService;

    @Value("http://localhost:8080/")
    private String imgPre;

    @RequestMapping("getPayInfo")
    public Result getPayInfo(String orderId){
        Map<String,String> map = payService.getPayInfo(orderId);
        if (map==null||map.size()==0) return Result.failure();
        if (!StringTool.isNotNull(map.get("QRCodeAddress"))) return Result.statusIsOne("订单支付失败，请稍后重试!");
        return Result.ok(map,imgPre);
    }

    @RequestMapping("getPayResult")
    public Result getPayResult(String orderId,Integer tryNums){
        if (tryNums>3) {
            return Result.statusIsOne("订单支付失败，请稍后重试!");
        }
        PayRespVo respVo = payService.getPayResult(orderId);
        if (respVo==null) return Result.failure();
        if (tryNums==3&&respVo.getOrderStatus()!=1){
            payService.updateOrderStatus(orderId,2);
        }
        return Result.ok(respVo);
    }

}
