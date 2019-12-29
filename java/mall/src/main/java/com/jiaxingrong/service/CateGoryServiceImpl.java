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

    @Override
    public Map category(Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        CateGory cateGory = cateGoryMapper.selectByPrimaryKey(id);
        CateGoryExample cateGoryExample = new CateGoryExample();
        CateGoryExample.Criteria criteria = cateGoryExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        List<CateGory> cateGories = null;
        if (cateGory.getPid()==0){
            criteria.andPidEqualTo(id);
            cateGories = cateGoryMapper.selectByExample(cateGoryExample);
            map.put("currentCategory",cateGories.get(0));
            map.put("brotherCategory",cateGories);
            map.put("parentCategory",cateGory);
        }else {
            map.put("currentCategory",cateGory);
            criteria.andPidEqualTo(cateGory.getPid());
            cateGories = cateGoryMapper.selectByExample(cateGoryExample);
            CateGory parentCategory = cateGoryMapper.selectByPrimaryKey(cateGory.getPid());
            map.put("parentCategory",parentCategory);
        }
        map.put("brotherCategory",cateGories);
        return map;
    }

    @Override
    public Map index() {
        HashMap<String, Object> map = new HashMap<>();
        CateGoryExample cateGoryExample = new CateGoryExample();
        CateGoryExample.Criteria criteria = cateGoryExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andPidEqualTo(0);
        List<CateGory> cateGories = cateGoryMapper.selectByExample(cateGoryExample);
        map.put("currentCategory",cateGories.get(0));
        map.put("categoryList",cateGories);
        CateGoryExample cateGoryExample1 = new CateGoryExample();
        CateGoryExample.Criteria criteria1 = cateGoryExample1.createCriteria();
        criteria1.andDeletedEqualTo(false);
        criteria1.andPidEqualTo(cateGories.get(0).getId());

        map.put("currentSubCategory",cateGoryMapper.selectByExample(cateGoryExample1));
        return map;
    }

    @Override
    public Map current(Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        CateGory cateGory = cateGoryMapper.selectByPrimaryKey(id);
        map.put("currentCategory",cateGory);
        CateGoryExample cateGoryExample = new CateGoryExample();
        CateGoryExample.Criteria criteria = cateGoryExample.createCriteria();
        criteria.andPidEqualTo(id);
        criteria.andDeletedEqualTo(false);
        List<CateGory> cateGories = cateGoryMapper.selectByExample(cateGoryExample);
        map.put("currentSubCategory",cateGories);
        return map;
    }
}
