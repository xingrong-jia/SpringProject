package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.CouponMapper;
import com.jiaxingrong.model.*;
import com.jiaxingrong.utils.DateUtils;
import com.jiaxingrong.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponMapper couponMapper;

    @Override
    public Map list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        CouponExample couponExample = new CouponExample();
        couponExample.setOrderByClause(laypage.getSort()+" "+laypage.getOrder());
        CouponExample.Criteria criteria = couponExample.createCriteria();
        if (laypage.getType()!=null) {
            criteria.andTypeEqualTo(laypage.getType());
        }
        if (laypage.getStatus()!=null) {
            criteria.andStatusEqualTo(laypage.getStatus());
        }
        if (StringTool.isNotNull(laypage.getName())){
            criteria.andNameLike("%"+laypage.getName()+"%");
        }
        criteria.andDeletedEqualTo(false);
        List<Coupon> coupons = couponMapper.selectByExample(couponExample);
        PageInfo<Coupon> couponPageInfo = new PageInfo<>(coupons);
        long total = couponPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", coupons);
        return map;
    }

    @Override
    public Coupon getCoupon(Integer id) {
        Coupon coupon = couponMapper.selectByPrimaryKey(id);
        return coupon;
    }


    @Override
    public Coupon addCoupon(@RequestBody  Coupon coupon) {
        coupon.setAddTime(new Date());
        coupon.setUpdateTime(new Date());
        coupon.setStartTime(new Date());

        Date date = DateUtils.getNDayTime(9);
        coupon.setEndTime(date);
        coupon.setDeleted(false);
        couponMapper.insertSelective(coupon);
        return coupon;
    }

    @Override
    public Coupon updateCoupon(@RequestBody  Coupon coupon) {
        coupon.setUpdateTime(new Date());
        couponMapper.updateByPrimaryKeySelective(coupon);
        return coupon;
    }

    @Override
    public int deleteCoupon(@RequestBody Coupon coupon) {
        coupon.setUpdateTime(new Date());
        coupon.setDeleted(true);
        return couponMapper.updateByPrimaryKeySelective(coupon);
    }
}
