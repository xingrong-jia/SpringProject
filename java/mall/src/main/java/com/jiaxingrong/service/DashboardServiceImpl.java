package com.jiaxingrong.service;

import com.jiaxingrong.mapper.GoodsMapper;
import com.jiaxingrong.mapper.Goods_productMapper;
import com.jiaxingrong.mapper.OrderMapper;
import com.jiaxingrong.mapper.UserMapper;
import com.jiaxingrong.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService{

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    Goods_productMapper goods_productMapper;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public Dashboard dashboard() {
        Dashboard dashboard = new Dashboard();
        GoodsExample goodsExample = new GoodsExample();
        long goodsTotal = goodsMapper.countByExample(goodsExample);
        long userTotal = userMapper.countByExample(new UserExample());
        long productTotal = goods_productMapper.countByExample(new Goods_productExample());
        long orderTotal = orderMapper.countByExample(new OrderExample());
        dashboard.setGoodsTotal(goodsTotal);
        dashboard.setUserTotal(userTotal);
        dashboard.setProductTotal(productTotal);
        dashboard.setOrderTotal(orderTotal);
        return dashboard;
    }
}
