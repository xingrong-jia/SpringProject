package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.BrandMapper;
import com.jiaxingrong.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandMapper brandMapper;

    /**
     * 根据分页情况查询响应的数据
     *
     * @param laypage
     * @return
     */
    @Override
    public Map<String, Object> list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        BrandExample brandExample = new BrandExample();
        brandExample.setOrderByClause(laypage.getSort()+" "+laypage.getOrder());
        if (laypage.getName() != null) {
            brandExample.createCriteria().andNameLike("%" + laypage.getName() + "%");
        }
        if (laypage.getId() != null) {
            brandExample.createCriteria().andIdEqualTo(laypage.getId());
        }
        List<Brand> brands = brandMapper.selectByExample(brandExample);
        PageInfo<Brand> addressPageInfo = new PageInfo<>(brands);
        long total = addressPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", brands);
        return map;
    }
}
