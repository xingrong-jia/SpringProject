package com.jiaxingrong.controller.admin;

import com.jiaxingrong.anntation.LogRecord;
import com.jiaxingrong.model.Admin;
import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.InfoData;
import com.jiaxingrong.service.AdminService;
import com.jiaxingrong.shiro.CustomToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("admin/auth")
public class AuthController {

    @Autowired
    AdminService adminService;

    @LogRecord
    @RequestMapping("login")
    public BaseReqVo login(@RequestBody Admin admin) {
        boolean b = adminService.login(admin);
        CustomToken token = new CustomToken(admin.getUsername(), admin.getPassword(), "admin");
        BaseReqVo baseReqVo = new BaseReqVo<>();
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            baseReqVo.setErrno(1);
            baseReqVo.setErrmsg("用户名或密码错误，请重试！");
            return baseReqVo;
        }
        baseReqVo.setData(subject.getSession().getId());
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("info")
    public BaseReqVo info(String token) {
        Subject subject = SecurityUtils.getSubject();
        Admin admin = (Admin) subject.getPrincipal();
        InfoData infoData = adminService.info(token);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setData(infoData);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    @LogRecord
    @RequestMapping("logout")
    public BaseReqVo logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
}
