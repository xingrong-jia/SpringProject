package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.BrandMapper;
import com.jiaxingrong.model.*;
import com.jiaxingrong.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        BrandExample.Criteria criteria = brandExample.createCriteria();
        if (StringTool.isNotNull(laypage.getName())) {
            criteria.andNameLike("%" + laypage.getName() + "%");
        }
        if (laypage.getId() != null) {
            criteria.andIdEqualTo(laypage.getId());
        }
        criteria.andDeletedEqualTo(false);
        List<Brand> brands = brandMapper.selectByExample(brandExample);
        PageInfo<Brand> addressPageInfo = new PageInfo<>(brands);
        long total = addressPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", brands);
        return map;
    }

    /**
     * 添加品牌商
     * @param brand
     * @return
     */
    @Override
    public Brand addBrand(Brand brand) {
        brand.setAddTime(new Date());
        brand.setUpdateTime(new Date());
        brand.setSortOrder((byte) 50);
        brand.setDeleted(false);
        brandMapper.insert(brand);
        brand = brandMapper.selectByPrimaryKey(brand.getId());
        return brand;
    }

    /**
     * 更新品牌商信息
     * @param brand
     * @return
     */
    @Override
    public int updateBrand(Brand brand) {
        int update = brandMapper.updateByPrimaryKey(brand);
        return update;
    }

    /**
     * 删除品牌商 假删除 即将其deleteId变为true
     * @param brand
     * @return
     */
    @Override
    public int deleteBrand(Brand brand) {
        brand.setDeleted(true);
        int update = brandMapper.updateByPrimaryKeySelective(brand);
        return update;
    }

    @Override
    public List<Brand> selectBrands() {
        return brandMapper.selectBrands();
    }

    @Override
    public Map detail(Integer id) {
        HashMap<String, Brand> map = new HashMap<>();
        Brand brand = brandMapper.selectByPrimaryKey(id);
        map.put("brand",brand);
        return map;
    }
}
