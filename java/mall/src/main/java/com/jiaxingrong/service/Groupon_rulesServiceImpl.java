package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.GoodsMapper;
import com.jiaxingrong.mapper.Groupon_rulesMapper;
import com.jiaxingrong.model.*;
import com.jiaxingrong.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Groupon_rulesServiceImpl implements Groupon_rulesService {

    @Autowired
    Groupon_rulesMapper groupon_rulesMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public Map list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        Groupon_rulesExample groupon_rulesExample = new Groupon_rulesExample();
        groupon_rulesExample.setOrderByClause(laypage.getSort()+" "+laypage.getOrder());
        Groupon_rulesExample.Criteria criteria = groupon_rulesExample.createCriteria();
        if (laypage.getGoodsId()!=null) {
            criteria.andGoodsIdEqualTo(laypage.getGoodsId());
        }
        criteria.andDeletedEqualTo(false);
        List<Groupon_rules> groupon_rules = groupon_rulesMapper.selectByExample(groupon_rulesExample);
        PageInfo<Groupon_rules> groupon_rulesPageInfo = new PageInfo<>(groupon_rules);
        long total = groupon_rulesPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", groupon_rules);
        return map;
    }

    @Override
    public Groupon_rules addGroupon_rules(Groupon_rules groupon_rules) {
        groupon_rules.setAddTime(new Date());
        groupon_rules.setUpdateTime(new Date());
        groupon_rules.setDeleted(false);
        Goods goods = goodsMapper.selectByPrimaryKey(groupon_rules.getGoodsId());
        groupon_rules.setGoodsName(goods.getName());
        groupon_rules.setPicUrl(goods.getPicUrl());
        groupon_rulesMapper.insertSelective(groupon_rules);
        return groupon_rules;
    }

    @Override
    public Groupon_rules updateGroupon_rules(Groupon_rules groupon_rules) {
        Goods goods = goodsMapper.selectByPrimaryKey(groupon_rules.getGoodsId());
        groupon_rules.setGoodsName(goods.getName());
        groupon_rules.setPicUrl(goods.getPicUrl());
        groupon_rules.setUpdateTime(new Date());
        groupon_rulesMapper.updateByPrimaryKeySelective(groupon_rules);
        return groupon_rules;
    }

    @Override
    public int deleteGroupon_rules(Groupon_rules groupon_rules) {
        groupon_rules.setUpdateTime(new Date());
        groupon_rules.setDeleted(true);

        return groupon_rulesMapper.updateByPrimaryKeySelective(groupon_rules);
    }
}
