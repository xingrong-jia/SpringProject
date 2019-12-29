package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.AdminPassword;
import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("admin/profile")
public class  ProfileController {

    @Autowired
    AdminService adminService;

    @RequestMapping("password")
    public BaseReqVo password(@RequestBody AdminPassword AdminPassword) {
        int b = adminService.changeAdminPassword(AdminPassword);
        BaseReqVo<String> baseReqVo = new BaseReqVo<>();
        if (b==1) {
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        } else if (b==2){
            baseReqVo.setErrno(1);
            baseReqVo.setErrmsg("旧密码错误！");
        }else if (b==3){
            baseReqVo.setErrno(1);
            baseReqVo.setErrmsg("新密码和确认新密码不一致！");
        }else {
            baseReqVo.setErrno(1);
            baseReqVo.setErrmsg("服务器正忙，请重试！");
        }
        return baseReqVo;
    }

}
