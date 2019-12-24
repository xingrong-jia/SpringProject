package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.CollectMapper;
import com.jiaxingrong.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    CollectMapper collectMapper;

    /**
     * 根据分页情况查询响应的数据
     *
     * @param laypage
     * @return
     */
    @Override
    public Map<String, Object> list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        CollectExample collectExample = new CollectExample();
        collectExample.setOrderByClause(laypage.getSort()+" "+laypage.getOrder());
        if (laypage.getValueId() != null) {
            collectExample.createCriteria().andValueIdEqualTo(laypage.getValueId());
        }
        if (laypage.getUserId() != null) {
            collectExample.createCriteria().andUserIdEqualTo(laypage.getUserId());
        }
        List<Collect> collects = collectMapper.selectByExample(collectExample);
        PageInfo<Collect> addressPageInfo = new PageInfo<>(collects);
        long total = addressPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", collects);
        return map;
    }
}
