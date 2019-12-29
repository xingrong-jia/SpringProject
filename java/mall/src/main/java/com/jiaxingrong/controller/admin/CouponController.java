package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.Coupon;
import com.jiaxingrong.model.Coupon_user;
import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.service.CouponService;
import com.jiaxingrong.service.Coupon_userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    @Autowired
    Coupon_userService coupon_userService;

    @RequestMapping("list")
    public BaseReqVo list(Laypage laypage){
        Map map = couponService.list(laypage);
        return BaseReqVo.ok(map);
    }

    @RequestMapping("read")
    public BaseReqVo read(Integer id){
        Coupon coupon = couponService.getCoupon(id);
        return BaseReqVo.ok(coupon);
    }



    @RequestMapping("listuser")
    public BaseReqVo listuser(Laypage laypage){
        Map map = coupon_userService.list(laypage);
        return BaseReqVo.ok(map);
    }



    @RequestMapping("create")
    public BaseReqVo create(@RequestBody Coupon coupon){
        coupon = couponService.addCoupon(coupon);
        return BaseReqVo.ok(coupon);
    }

    @RequestMapping("update")
    public BaseReqVo update(@RequestBody Coupon coupon){
        coupon = couponService.updateCoupon(coupon);
        return BaseReqVo.ok(coupon);
    }

    @RequestMapping("delete")
    public BaseReqVo delete(@RequestBody Coupon coupon){
        couponService.deleteCoupon(coupon);
        return BaseReqVo.ok();
    }
}
