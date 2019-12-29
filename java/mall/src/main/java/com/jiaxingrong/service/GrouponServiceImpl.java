package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.GrouponMapper;
import com.jiaxingrong.mapper.Groupon_rulesMapper;
import com.jiaxingrong.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GrouponServiceImpl implements GrouponService {

    @Autowired
    GrouponMapper grouponMapper;

    @Autowired
    Groupon_rulesMapper groupon_rulesMapper;

    @Override
    public Map list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        GrouponExample grouponExample = new GrouponExample();
        grouponExample.setOrderByClause(laypage.getSort()+" "+laypage.getOrder());
        GrouponExample.Criteria criteria = grouponExample.createCriteria();
        if (laypage.getGoodsId()!=null) {
            Groupon_rulesExample groupon_rulesExample = new Groupon_rulesExample();
            Groupon_rulesExample.Criteria criteria1 = groupon_rulesExample.createCriteria();
            criteria1.andGoodsIdEqualTo(laypage.getGoodsId());
            criteria1.andDeletedEqualTo(false);
            List<Groupon_rules> groupon_rules = groupon_rulesMapper.selectByExample(groupon_rulesExample);
            ArrayList<Integer> list = new ArrayList<>();
            for (Groupon_rules groupon_rule : groupon_rules) {
                list.add(groupon_rule.getId());
            }
            criteria.andGrouponIdIn(list);
        }
        criteria.andDeletedEqualTo(false);
        List<Groupon> groupon_rules = grouponMapper.selectByExample(grouponExample);
        PageInfo<Groupon> groupon_rulesPageInfo = new PageInfo<>(groupon_rules);
        long total = groupon_rulesPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", groupon_rules);
        return map;
    }

    @Override
    public Groupon addGroupon(Groupon groupon) {
        return null;
    }

    @Override
    public Groupon updateGroupon(Groupon groupon) {
        return null;
    }

    @Override
    public int deleteGroupon(Groupon groupon) {
        return 0;
    }
}
