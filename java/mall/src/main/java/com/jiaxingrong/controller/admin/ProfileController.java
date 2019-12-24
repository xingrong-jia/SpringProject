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
            baseReqVo.setData("4b7d719e-53b7-4019-9677-6309b2445b45");
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        } else {
            baseReqVo.setErrno(1);
            baseReqVo.setErrmsg("用户名或密码错误，请重试！");
        }
        return baseReqVo;
    }

}
