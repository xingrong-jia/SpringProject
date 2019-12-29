package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.RoleMapper;
import com.jiaxingrong.model.*;
import com.jiaxingrong.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List options() {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andDeletedEqualTo(false);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        ArrayList<Map> maps = new ArrayList<>();
        for (Role role : roles) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("value",role.getId());
            map.put("label",role.getName());
            maps.add(map);
        }
        return maps;
    }

    @Override
    public Map list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        RoleExample roleExample = new RoleExample();
        roleExample.setOrderByClause(laypage.getSort()+" "+laypage.getOrder());
        RoleExample.Criteria criteria = roleExample.createCriteria();
        if (StringTool.isNotNull(laypage.getName())) {
            criteria.andNameLike("%"+laypage.getName()+"%");
        }
        criteria.andDeletedEqualTo(false);
        List<Role> roles =  roleMapper.selectByExample(roleExample);
        PageInfo<Role> rolePageInfo = new PageInfo<>(roles);
        long total = rolePageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", roles);
        return map;
    }

    @Override
    public Role addRole(Role role) {
        role.setEnabled(true);
        role.setAddTime(new Date());
        role.setUpdateTime(new Date());
        role.setDeleted(false);
        roleMapper.insertSelective(role);
        return role;
    }

    @Override
    public Role updateRole(Role role) {
        role.setUpdateTime(new Date());
        roleMapper.updateByPrimaryKeySelective(role);
        return role;
    }

    @Override
    public int deleteRole(Role role) {

        role.setUpdateTime(new Date());
        role.setDeleted(true);

        return roleMapper.updateByPrimaryKeySelective(role);
    }


}
