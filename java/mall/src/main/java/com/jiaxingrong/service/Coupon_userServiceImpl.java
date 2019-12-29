package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.Coupon_userMapper;
import com.jiaxingrong.model.*;
import com.jiaxingrong.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Coupon_userServiceImpl implements Coupon_userService {

    @Autowired
    Coupon_userMapper coupon_userMapper;

    @Override
    public Map list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        Coupon_userExample coupon_userExample = new Coupon_userExample();
        coupon_userExample.setOrderByClause(laypage.getSort()+" "+laypage.getOrder());
        Coupon_userExample.Criteria criteria = coupon_userExample.createCriteria();
        if (laypage.getId()!=null) {
            criteria.andIdEqualTo(laypage.getId());
        }
        if (laypage.getStatus()!=null) {
            criteria.andStatusEqualTo(laypage.getStatus());
        }
        criteria.andDeletedEqualTo(false);
        List<Coupon_user> coupon_usersc = coupon_userMapper.selectByExample(coupon_userExample);
        PageInfo<Coupon_user> coupon_userPageInfo = new PageInfo<>(coupon_usersc);
        long total = coupon_userPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", coupon_usersc);
        return map;
    }
}
