package com.stylefeng.guns.rest.common.persistence.control;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jiaxingrong.utils.StringTool;
import com.stylefeng.guns.alipay.AliPayService;
import com.stylefeng.guns.cinema.vo.CinemasReqVo;
import com.stylefeng.guns.mq.MqService;
import com.stylefeng.guns.promo.PromoService;
import com.stylefeng.guns.promo.vo.PromoReqVo;
import com.stylefeng.guns.promo.vo.PromoRespVo;
import com.stylefeng.guns.rest.model.Result;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-13 12:46
 */
@Slf4j
@RestController
@RequestMapping("promo")
public class PromoController {

    @Reference(interfaceClass = PromoService.class, retries = 1)
    private PromoService promoService;

    @Reference(interfaceClass = UserService.class, retries = 1)
    private UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Reference(interfaceClass = MqService.class, retries = 1)
    private MqService mqService;



    @RequestMapping("getPromo")
    public Result getPromo(CinemasReqVo reqVo) {
        List<PromoRespVo> respVos = promoService.getPromos(reqVo);
        if (respVos == null) return Result.failure();
        if (respVos.size() == 0) return Result.statusIsOne("暂无秒杀活动！");
        return Result.ok(respVos);
    }

    @RequestMapping("publishPromoStock")
    public Result publishPromoStock(Integer cinemaId) {
        Integer status = promoService.publishPromoStock(cinemaId);
        if (status == null) return Result.failure();
        if (status == 0) return Result.ok("发布成功！");
        else return Result.statusIsOne("发布失败！");
    }

    @RequestMapping("generateToken")
    public Result generateToken(Integer promoId, @RequestHeader String Authorization) {
        String loginToken = Authorization.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(loginToken);
        Integer userId = userService.queryUserId(username);
        String token = promoService.generateToken(promoId, userId);
        if (token == null) return Result.failure();
        //if (token.length() == 0 || "".equals(token)) return Result.statusIsOne("获取失败");
        return Result.ok(token);
    }

    @RequestMapping("createOrder")
    public Result createOrder(PromoReqVo reqVo, @RequestHeader String Authorization) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String loginToken = Authorization.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(loginToken);
        Integer userId = userService.queryUserId(username);
        reqVo.setUserId(userId);
        Integer status = promoService.createOrder(reqVo);
        if (status == null) return Result.failure();
        if (status == -4) return Result.statusIsOne("订单数量不符合要求，最多只能拍十件！");
        if (status == -3) return Result.statusIsOne("库存小于您购买的数量，请修改！");
        if (status == -2) return Result.statusIsOne("商品已经售空！");
        if (status == -1) return Result.statusIsOne("无秒杀令牌，请刷新尝试获取！");
        if (status == 1) {
            Integer amount = reqVo.getAmount();
            Integer promoId = reqVo.getPromoId();
/*            Boolean redisStock = mqService.sendRedisStock(promoId, amount);
            if (redisStock){
                log.info("redis-->活动Id:"+promoId+":售出"+amount);
            }*/

            //Boolean mysqlStock = mqService.sendMysqlStock(promoId, amount, userId);
            Boolean mysqlStock = mqService.sendMysqlStockTransaction(promoId, amount, userId);
            if (mysqlStock) {
                log.info("mysql-->活动Id:" + promoId + ",售出:" + amount + ",下单用户id：" + userId);
            }
        }
        //Thread.sleep(10);
        stopWatch.stop();

        log.info("秒杀下单接口请求耗时 -> {} ms", stopWatch.getTotalTimeMillis());
        return Result.ok("下单成功,兑换码稍后已短信发送，您也可在我的订单中查询！");
    }

}
