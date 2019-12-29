package com.jiaxingrong.service;

import com.jiaxingrong.mapper.PermissionMapper;
import com.jiaxingrong.mapper.SystemPermissionMapper;
import com.jiaxingrong.model.Permission;
import com.jiaxingrong.model.PermissionExample;
import com.jiaxingrong.model.SystemPermission;
import com.jiaxingrong.model.SystemPermissionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    SystemPermissionMapper systemPermissionMapper;

    List<SystemPermission> systemPermissions;

    @Override
    public Map permissions(Integer roleId) {
        HashMap<String, List> map = new HashMap<>();

        if (systemPermissions == null) {
            this.systemPermissions = getSystemPermissions2();
        }

        map.put("systemPermissions", systemPermissions);

        List  strings =  getPermissions(roleId);

        map.put("assignedPermissions", strings);

        return map;
    }

    private List getPermissions(Integer roleId) {
        PermissionExample permissionExample = new PermissionExample();
        PermissionExample.Criteria criteria = permissionExample.createCriteria();
        criteria.andRoleIdEqualTo(roleId);
        criteria.andDeletedEqualTo(false);
        List<Permission> permissions = permissionMapper.selectByExample(permissionExample);
        ArrayList<String> strings = new ArrayList<>();
        if (permissions.size() == 1 && permissions.get(0).equals("*")) {
            PermissionExample permissionExample1 = new PermissionExample();
            PermissionExample.Criteria criteria1 = permissionExample1.createCriteria();
            criteria1.andPermissionNotEqualTo("*");
            criteria1.andDeletedEqualTo(false);
            permissions = permissionMapper.selectByExample(permissionExample1);
        }
        for (Permission permission : permissions) {
            strings.add(permission.getPermission());
        }
        return strings;
    }

    @Override
    public int addPermissions(Permission permission) {
        String[] newPermissions = permission.getPermissions();
        Integer roleId = permission.getRoleId();
        int a = 0;

        List<String> toSqlPermissions = getPermissions(roleId);

        for (String s : newPermissions) {
            if (!toSqlPermissions.contains(s)){
                Permission permission1 = new Permission();
                permission1.setRoleId(roleId);
                permission1.setPermission(s);
                permission1.setAddTime(new Date());
                permission1.setUpdateTime(new Date());
                permission1.setDeleted(false);
                a += permissionMapper.insertSelective(permission1);
            }
            toSqlPermissions.remove(s);
        }
        for (String toSqlPermission : toSqlPermissions) {
            Permission permission1 = new Permission();
            permission1.setRoleId(roleId);
            permission1.setDeleted(true);
            permission1.setUpdateTime(new Date());
            permission1.setPermission(toSqlPermission);
            permissionMapper.updatePermissionByRoleIdAndPermission(permission1);
        }
        return a;
    }

    private List getSystemPermissions() {
        long l = System.currentTimeMillis();
        SystemPermissionExample systemPermissionExample = new SystemPermissionExample();
        SystemPermissionExample.Criteria criteria = systemPermissionExample.createCriteria();
        criteria.andPidEqualTo(0);
        List<SystemPermission> systemPermissions = systemPermissionMapper.selectByExample(systemPermissionExample);
        for (SystemPermission systemPermission : systemPermissions) {
            SystemPermissionExample systemPermissionExample1 = new SystemPermissionExample();
            SystemPermissionExample.Criteria criteria1 = systemPermissionExample1.createCriteria();
            criteria1.andPidEqualTo(systemPermission.getPrimaryId());
            List<SystemPermission> childrens = systemPermissionMapper.selectByExample(systemPermissionExample1);
            for (SystemPermission children : childrens) {
                SystemPermissionExample systemPermissionExample2 = new SystemPermissionExample();
                SystemPermissionExample.Criteria criteria2 = systemPermissionExample2.createCriteria();
                criteria2.andPidEqualTo(children.getPrimaryId());
                List<SystemPermission> api = systemPermissionMapper.selectByExample(systemPermissionExample2);
                children.setChildren(api);
            }
            systemPermission.setChildren(childrens);
        }
        long l1 = System.currentTimeMillis();
        System.out.println("执行时间:"+(l1-l));
        return systemPermissions;
    }

    private List getSystemPermissions2() {
        long l = System.currentTimeMillis();
        List<SystemPermission> systemPermissions = systemPermissionMapper.selectByExample(new SystemPermissionExample());
        ArrayList<SystemPermission> permissions = new ArrayList<>();
        for (SystemPermission systemPermission : systemPermissions) {
            if (systemPermission.getPid()==0){
                permissions.add(systemPermission);
            }
        }
        for (SystemPermission permission : permissions) {
            Integer primaryId = permission.getPrimaryId();
            ArrayList<SystemPermission> childrens = new ArrayList<>();
            for (SystemPermission systemPermission : systemPermissions) {
                if (systemPermission.getPid()==primaryId){
                    childrens.add(systemPermission);
                }
            }
            for (SystemPermission children : childrens) {
                Integer primaryId1 = children.getPrimaryId();
                ArrayList<SystemPermission> arrayList = new ArrayList<>();
                for (SystemPermission systemPermission1 : systemPermissions) {
                    if (systemPermission1.getPid()==primaryId1){
                        arrayList.add(systemPermission1);
                    }
                }
                children.setChildren(arrayList);
            }
            permission.setChildren(childrens);
        }
        long l1 = System.currentTimeMillis();
        System.out.println("执行时间:"+(l1-l));
        return permissions;
    }

}
