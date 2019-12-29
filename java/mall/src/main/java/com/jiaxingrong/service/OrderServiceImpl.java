package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.OrderMapper;
import com.jiaxingrong.mapper.Order_goodsMapper;
import com.jiaxingrong.mapper.UserMapper;
import com.jiaxingrong.model.*;
import com.jiaxingrong.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    Order_goodsMapper order_goodsMapper;

    @Override
    public Map<String, Object> list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        OrderExample orderExample = new OrderExample();
        orderExample.setOrderByClause(laypage.getSort()+" "+laypage.getOrder());
        OrderExample.Criteria criteria = orderExample.createCriteria();
        if (laypage.getUserId()!=null) {
            criteria.andUserIdEqualTo(laypage.getUserId());
        }
        if (laypage.getOrderSn() != null) {
            criteria.andIdEqualTo(laypage.getOrderSn());
        }
        if (laypage.getOrderStatusArray()!=null){
            criteria.andOrderStatusIn(laypage.getOrderStatusArray());
        }
        criteria.andDeletedEqualTo(false);
        List<Order> orders = orderMapper.selectByExample(orderExample);
        PageInfo<Order> orderPageInfo = new PageInfo<>(orders);
        long total = orderPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", orders);
        return map;
    }


    @Override
    public Map detail(Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        Order_goodsExample order_goodsExample = new Order_goodsExample();
        Order_goodsExample.Criteria criteria = order_goodsExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andOrderIdEqualTo(id);
        List<Order_goods> order_goods = order_goodsMapper.selectByExample(order_goodsExample);
        map.put("orderGoods",order_goods);

        Order order = orderMapper.selectByPrimaryKey(id);
        map.put("order",order);
        User user = userMapper.selectByPrimaryKey(order.getUserId());
        map.put("user",user);
        return map;
    }
}
