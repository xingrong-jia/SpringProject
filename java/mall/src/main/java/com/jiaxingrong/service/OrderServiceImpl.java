package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.OrderMapper;
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
}
