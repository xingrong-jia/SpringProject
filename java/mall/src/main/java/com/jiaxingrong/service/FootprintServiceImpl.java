package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.FootprintMapper;
import com.jiaxingrong.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FootprintServiceImpl implements FootprintService {


    @Autowired
    FootprintMapper footprintMapper;

    /**
     * 根据分页情况查询响应的数据
     *
     * @param laypage
     * @return
     */
    @Override
    public Map<String, Object> list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        FootprintExample footprintExample = new FootprintExample();
        footprintExample.setOrderByClause(laypage.getSort()+" "+laypage.getOrder());
        if (laypage.getGoodsId() != null) {
            footprintExample.createCriteria().andGoodsIdEqualTo(laypage.getGoodsId());
        }
        if (laypage.getUserId() != null) {
            footprintExample.createCriteria().andUserIdEqualTo(laypage.getUserId());
        }
        List<Footprint> footprints = footprintMapper.selectByExample(footprintExample);
        PageInfo<Footprint> addressPageInfo = new PageInfo<>(footprints);
        long total = addressPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", footprints);
        return map;
    }
}
