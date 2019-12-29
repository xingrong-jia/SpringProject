package com.jiaxingrong.service;

import com.jiaxingrong.model.Coupon;
import com.jiaxingrong.model.Laypage;

import java.util.Map;

public interface CouponService {
    Map list(Laypage laypage);

    Coupon getCoupon(Integer id);

    Coupon addCoupon(Coupon coupon);

    Coupon updateCoupon(Coupon coupon);

    int deleteCoupon(Coupon coupon);
}
