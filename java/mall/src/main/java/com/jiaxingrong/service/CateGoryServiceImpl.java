package com.jiaxingrong.service;

import com.jiaxingrong.mapper.CateGoryMapper;
import com.jiaxingrong.model.CateGory;
import com.jiaxingrong.model.CateGoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CateGoryServiceImpl implements CateGoryService {

    @Autowired
    CateGoryMapper cateGoryMapper;

    @Override
    public List<CateGory> list() {
        CateGoryExample example = new CateGoryExample();
        CateGoryExample.Criteria criteria = example.createCriteria();
        criteria.andLevelEqualTo("L1");
        criteria.andDeletedEqualTo(false);
        List<CateGory> list = cateGoryMapper.selectByExample(example);
        for (CateGory cateGory : list) {
            CateGoryExample example1 = new CateGoryExample();
            CateGoryExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andLevelEqualTo("L2");
            criteria1.andPidEqualTo(cateGory.getId());
            criteria1.andDeletedEqualTo(false);
            List<CateGory> list1 = cateGoryMapper.selectByExample(example1);
            cateGory.setChildren(list1);
        }
        return list;
    }

    /**
     * 获取一级类目
     * @return
     */
    @Override
    public List<Map> listL1() {
        CateGoryExample example = new CateGoryExample();
        CateGoryExample.Criteria criteria = example.createCriteria();
        criteria.andLevelEqualTo("L1");
        criteria.andDeletedEqualTo(false);
        List<CateGory> list = cateGoryMapper.selectByExample(example);
        List<Map> maps = new ArrayList<>();
        for (CateGory cateGory : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("value",cateGory.getId());
            map.put("label",cateGory.getName());
            maps.add(map);
        }
        return maps;
    }

    @Override
    public CateGory addCateGory(CateGory cateGory) {
        cateGory.setSortOrder((byte) 50);
        cateGory.setAddTime(new Date());
        cateGory.setUpdateTime(new Date());
        cateGory.setDeleted(false);
        cateGoryMapper.insert(cateGory);
        return cateGory;
    }

    /**
     * 修改类目信息
     * @param cateGory
     * @return
     */
    @Override
    public int updateCateGory(CateGory cateGory) {
        cateGory.setUpdateTime(new Date());
        int update = cateGoryMapper.updateByPrimaryKeySelective(cateGory);
        return update;
    }
    /**
     * 删除类目信息
     * @param cateGory
     * @return
     */
    @Override
    public int deleteCateGory(CateGory cateGory) {
        cateGory.setDeleted(true);
        int update = cateGoryMapper.updateByPrimaryKeySelective(cateGory);
        return update;
    }
}
