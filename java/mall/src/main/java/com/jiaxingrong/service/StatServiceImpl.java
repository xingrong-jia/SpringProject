package com.jiaxingrong.service;

import com.jiaxingrong.mapper.GoodsMapper;
import com.jiaxingrong.mapper.OrderMapper;
import com.jiaxingrong.mapper.Order_goodsMapper;
import com.jiaxingrong.mapper.UserMapper;
import com.jiaxingrong.model.*;
import com.jiaxingrong.utils.DateUtils;
import com.jiaxingrong.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class StatServiceImpl implements StatService {


    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    Order_goodsMapper order_goodsMapper;

    @Override
    public Map user() {
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("day");
        strings.add("users");
        map.put("columns", strings);
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andAddTimeBetween(DateUtils.getToDayStartDate(), DateUtils.getToDayEndDate());
        ArrayList<Object> objects = new ArrayList<>();
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("day", StringTool.Date2String(new Date()));
        map1.put("users", userMapper.countByExample(userExample));
        objects.add(map1);
        map.put("rows", objects);
        return map;
    }

    @Override
    public Map order() {
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("day");
        strings.add("orders");
        strings.add("customers");
        strings.add("amount");
        strings.add("pcr");
        map.put("columns", strings);
        ArrayList<Object> objects = new ArrayList<>();
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("day", StringTool.Date2String(new Date()));
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andAddTimeBetween(DateUtils.getToDayStartDate(), DateUtils.getToDayEndDate());
        criteria.andDeletedEqualTo(false);
        Long l = 0l;
        try {
            l = orderMapper.countByExample(orderExample);
        }catch (Exception e){

        }
        map1.put("orders", l);


        map1.put("customers", orderMapper.countUserIdDistinct(DateUtils.getToDayStartDate(), DateUtils.getToDayEndDate()));
        BigDecimal bigDecimal = orderMapper.selectActual_priceSum(false);
        map1.put("amount", bigDecimal);

        BigDecimal decimal = BigDecimal.valueOf(l);
        BigDecimal divide = bigDecimal.divide(decimal);
        map1.put("pcr", divide);
        objects.add(map1);
        map.put("rows", objects);
        return map;
    }

    @Override
    public Map goods() {
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("day");
        strings.add("orders");
        strings.add("products");
        strings.add("amount");
        map.put("columns", strings);
        ArrayList<Object> objects = new ArrayList<>();
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("day", StringTool.Date2String(new Date()));
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andAddTimeBetween(DateUtils.getToDayStartDate(), DateUtils.getToDayEndDate());
        map1.put("orders", orderMapper.countByExample(orderExample));
        List<Order> orders = orderMapper.selectByExample(orderExample);
        long products = 0l;
        for (Order order : orders) {
            Order_goodsExample order_goodsExample = new Order_goodsExample();
            Order_goodsExample.Criteria criteria1 = order_goodsExample.createCriteria();
            criteria1.andDeletedEqualTo(false);
            criteria1.andAddTimeBetween(DateUtils.getToDayStartDate(), DateUtils.getToDayEndDate());
            List<Order_goods> order_goods = order_goodsMapper.selectByExample(order_goodsExample);
            for (Order_goods order_good : order_goods) {
                products += order_good.getNumber();
            }
        }
        map1.put("products", products);

        map1.put("amount", orderMapper.selectActual_priceSum(false));
        objects.add(map1);
        map.put("rows", objects);
        return map;
    }
}
