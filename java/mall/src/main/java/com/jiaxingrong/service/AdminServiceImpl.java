package com.jiaxingrong.service;

import com.jiaxingrong.mapper.AdminMapper;
import com.jiaxingrong.model.Admin;
import com.jiaxingrong.model.AdminPassword;
import com.jiaxingrong.model.InfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    AdminMapper adminMapper;

    /**
     * 管理员登录校验
     * @param admin
     * @return
     */
    @Override
    public boolean login(Admin admin) {
        Integer integer = adminMapper.selectIdByUsernameAndPassword(admin);
        if (integer!=null) return true;
        return false;
    }

    /**
     * 获取管理员数据
     * @param token
     * @return
     */
    @Override
    public InfoData info(String token) {
        InfoData data = new InfoData();
        data.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.setName("admin");
        ArrayList<String> perms = new ArrayList<>();
        perms.add("*");
        data.setPerms(perms);
        ArrayList<String> roles = new ArrayList<>();
        roles.add("超级管理员");
        data.setRoles(roles);
        return data;
    }

    /**
     * 修改当前登录的管理员账户的密码
     * @param adminPassword
     * @return 1代表修改成功  2代表旧密码错误  3代表新密码和确认新密码不一致 0表示服务器异常
     */
    @Override
    public int changeAdminPassword(AdminPassword adminPassword) {
        return 1;
    }
}
