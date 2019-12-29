package com.jiaxingrong.service;

import com.jiaxingrong.mapper.SystemMapper;
import com.jiaxingrong.model.AdminConfig;
import com.jiaxingrong.model.System;
import com.jiaxingrong.model.SystemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    SystemMapper systemMapper;

    @Override
    public Map mall(AdminConfig adminConfig) {
        HashMap<String, Object> map = new HashMap<>();
        if (adminConfig==null){
            SystemExample systemExample = new SystemExample();
            SystemExample.Criteria criteria = systemExample.createCriteria();
            criteria.andDeletedEqualTo(false);
            criteria.andKeyNameLike("%"+"mall_mall"+"%");
            List<System> systems = systemMapper.selectByExample(systemExample);
            AdminConfig config = new AdminConfig();

            for (System system : systems) {
                map.put(system.getKeyName(),system.getKeyValue());
            }
            map.put("AdminConfig",null);
            return map;
        }

        updateSystem("litemall_mall_phone",adminConfig.getLitemall_mall_phone());
        updateSystem("litemall_mall_address",adminConfig.getLitemall_mall_address());
        updateSystem("litemall_mall_name",adminConfig.getLitemall_mall_name());
        updateSystem("litemall_mall_qq",adminConfig.getLitemall_mall_qq());

        map.put("AdminConfig",adminConfig);
        return map;
    }

    @Override
    public Map express(AdminConfig adminConfig) {
        HashMap<String, Object> map = new HashMap<>();
        if (adminConfig==null){
            SystemExample systemExample = new SystemExample();
            SystemExample.Criteria criteria = systemExample.createCriteria();
            criteria.andDeletedEqualTo(false);
            criteria.andKeyNameLike("%"+"mall_express"+"%");
            List<System> systems = systemMapper.selectByExample(systemExample);
            AdminConfig config = new AdminConfig();

            for (System system : systems) {
                map.put(system.getKeyName(),system.getKeyValue());
            }
            map.put("AdminConfig",null);
            return map;
        }

        updateSystem("litemall_express_freight_min",adminConfig.getLitemall_express_freight_min());
        updateSystem("litemall_express_freight_value",adminConfig.getLitemall_express_freight_value());

        map.put("AdminConfig",adminConfig);
        return map;
    }

    @Override
    public Map order(AdminConfig adminConfig) {
        HashMap<String, Object> map = new HashMap<>();
        if (adminConfig==null){
            SystemExample systemExample = new SystemExample();
            SystemExample.Criteria criteria = systemExample.createCriteria();
            criteria.andDeletedEqualTo(false);
            criteria.andKeyNameLike("%"+"mall_order"+"%");
            List<System> systems = systemMapper.selectByExample(systemExample);
            AdminConfig config = new AdminConfig();

            for (System system : systems) {
                map.put(system.getKeyName(),system.getKeyValue());
            }
            return map;
        }

        updateSystem("litemall_order_comment",adminConfig.getLitemall_order_comment());
        updateSystem("litemall_order_unpaid",adminConfig.getLitemall_order_unpaid());
        updateSystem("litemall_order_unconfirm",adminConfig.getLitemall_order_unconfirm());

        return map;
    }

    @Override
    public Map wx(AdminConfig adminConfig) {
        HashMap<String, Object> map = new HashMap<>();
        if (adminConfig==null){
            SystemExample systemExample = new SystemExample();
            SystemExample.Criteria criteria = systemExample.createCriteria();
            criteria.andDeletedEqualTo(false);
            criteria.andKeyNameLike("%"+"mall_wx"+"%");
            List<System> systems = systemMapper.selectByExample(systemExample);
            AdminConfig config = new AdminConfig();

            for (System system : systems) {
                map.put(system.getKeyName(),system.getKeyValue());
            }
            map.put("AdminConfig",null);
            return map;
        }

        updateSystem("litemall_wx_index_new",adminConfig.getLitemall_wx_index_new());
        updateSystem("litemall_wx_catlog_goods",adminConfig.getLitemall_wx_catlog_goods());
        updateSystem("litemall_wx_catlog_list",adminConfig.getLitemall_wx_catlog_list());
        updateSystem("litemall_wx_share",adminConfig.getLitemall_wx_share());
        updateSystem("litemall_wx_index_brand",adminConfig.getLitemall_wx_index_brand());
        updateSystem("litemall_wx_index_hot",adminConfig.getLitemall_wx_index_hot());
        updateSystem("litemall_wx_index_topic",adminConfig.getLitemall_wx_index_topic());

        map.put("AdminConfig",adminConfig);
        return map;
    }

    private void updateSystem(String s,String ss){
        SystemExample systemExample = new SystemExample();
        SystemExample.Criteria criteria = systemExample.createCriteria();
        criteria.andKeyNameEqualTo(s);
        criteria.andDeletedEqualTo(false);
        List<System> systems = systemMapper.selectByExample(systemExample);
        System system = systems.get(0);
        if (!system.getKeyValue().equals(ss)) {
            system.setUpdateTime(new Date());
            system.setKeyValue(ss);
            systemMapper.updateByPrimaryKeySelective(system);
        }
    }
}
