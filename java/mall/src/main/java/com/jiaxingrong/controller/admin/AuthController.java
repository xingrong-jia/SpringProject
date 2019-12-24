package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.Admin;
import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.InfoData;
import com.jiaxingrong.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("admin/auth")
public class AuthController {

    @Autowired
    AdminService adminService;

    @RequestMapping("login")
    public BaseReqVo login(@RequestBody Admin admin) {
        boolean b = adminService.login(admin);
        BaseReqVo<String> baseReqVo = new BaseReqVo<>();
        if (b) {
            baseReqVo.setData("4b7d719e-53b7-4019-9677-6309b2445b45");
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        } else {
            baseReqVo.setErrno(1);
            baseReqVo.setErrmsg("用户名或密码错误，请重试！");
        }
        return baseReqVo;
    }

    @RequestMapping("info")
    public BaseReqVo info(String token) {
        InfoData infoData = adminService.info(token);
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setData(infoData);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
}
