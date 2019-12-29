package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.AdminMapper;
import com.jiaxingrong.mapper.PermissionMapper;
import com.jiaxingrong.mapper.RoleMapper;
import com.jiaxingrong.mapper.SystemPermissionMapper;
import com.jiaxingrong.model.*;
import com.jiaxingrong.utils.StringTool;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    AdminMapper adminMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    SystemPermissionMapper systemPermissionMapper;

    /**
     * 管理员登录校验
     *
     * @param admin
     * @return
     */
    @Override
    public boolean login(Admin admin) {
        Integer integer = adminMapper.selectIdByUsernameAndPassword(admin);
        if (integer != null) return true;
        return false;
    }

    /**
     * 获取管理员数据
     *
     * @param token
     * @return
     */
    @Override
    public InfoData info(String token) {
        Subject subject = SecurityUtils.getSubject();
        Admin admin = (Admin) subject.getPrincipal();

        InfoData data = new InfoData();
        data.setAvatar(admin.getAvatar());
        data.setName(admin.getUsername());
        //String roleIds = admin.getRoleIds();
        //Integer[] integers = JsonUtils.convertToObject(roleIds, Integer[].class);
        List<String> roleId = permissionMapper.selectPermissionByRoleId(admin.getRoleIds(),false);
        List<String> strings = roleMapper.selectNameByIds(admin.getRoleIds());
        ArrayList<String> arrayList = new ArrayList<>();
        for (String s : roleId) {
            arrayList.add(systemPermissionMapper.selectApiById(s));
        }
        data.setPerms(arrayList);

        data.setRoles(strings);
        return data;
    }

    /**
     * 修改当前登录的管理员账户的密码
     *
     * @param adminPassword
     * @return 1代表修改成功  2代表旧密码错误  3代表新密码和确认新密码不一致 0表示服务器异常
     */
    @Override
    public int changeAdminPassword(AdminPassword adminPassword) {
        if (!adminPassword.getNewPassword().equals(adminPassword.getNewPassword2())) return 3;
        Subject subject = SecurityUtils.getSubject();
        Admin admin = (Admin) subject.getPrincipal();
        String password = admin.getPassword();
        if (!password.equals(adminPassword.getOldPassword())) return 2;
        admin.setPassword(adminPassword.getNewPassword());
        admin.setUpdateTime(new Date());
        int update = adminMapper.updateByPrimaryKeySelective(admin);
        return update;
    }

    @Override
    public Map list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        AdminExample adminExample = new AdminExample();
        adminExample.setOrderByClause(laypage.getSort()+" "+laypage.getOrder());
        AdminExample.Criteria criteria = adminExample.createCriteria();
        if (StringTool.isNotNull(laypage.getUsername())) {
            criteria.andUsernameLike("%"+laypage.getUsername()+"%");
        }
        criteria.andDeletedEqualTo(false);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        PageInfo<Admin> adminPageInfo = new PageInfo<>(admins);
        long total = adminPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", admins);
        return map;
    }

    @Override
    public Map addAdmin(Admin admin) {
        HashMap<String, Object> map = new HashMap<>();
        if (admin.getPassword().length()<6) {
            map.put("errno",2);
            return map;
        }
        if (admin.getUsername().length()<6){
            map.put("errno",3);
            return map;
        }
        admin.setAddTime(new Date());
        admin.setUpdateTime(new Date());
        admin.setDeleted(false);

        map.put("errno",adminMapper.insertSelective(admin));
        map.put("admin",admin);
        return map;
    }

    @Override
    public Map updateAdmin(Admin admin) {
        HashMap<String, Object> map = new HashMap<>();
        if (admin.getPassword().length()<6) {
            map.put("errno",2);
            return map;
        }
        if (admin.getUsername().length()<6){
            map.put("errno",3);
            return map;
        }
        admin.setUpdateTime(new Date());

        map.put("errno",adminMapper.updateByPrimaryKeySelective(admin));
        map.put("admin",admin);
        return map;
    }

    @Override
    public int deleteAdmin(Admin admin) {
        admin.setUpdateTime(new Date());
        admin.setDeleted(true);
        return adminMapper.updateByPrimaryKeySelective(admin);
    }


}
