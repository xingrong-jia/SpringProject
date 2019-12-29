package com.jiaxingrong.controller.admin;

import com.jiaxingrong.anntation.AdminRecord;
import com.jiaxingrong.model.Admin;
import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.Coupon;
import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/admin")
public class AdminController  {

    @Autowired
    AdminService adminService;

    @RequestMapping("list")
    public BaseReqVo list(Laypage laypage){
        Map map = adminService.list(laypage);
        return BaseReqVo.ok(map);
    }


    @AdminRecord
    @RequestMapping("create")
    public BaseReqVo create(@RequestBody Admin admin){
        Map map = adminService.addAdmin(admin);
        Integer errno = (Integer) map.get("errno");
        if (errno==1) return BaseReqVo.ok(admin);
        if (errno==2) return new BaseReqVo("管理员密码长度不能小于6位",601);
        return new BaseReqVo("管理员名称长度不能小于6位",601);
    }

    @AdminRecord
    @RequestMapping("update")
    public BaseReqVo update(@RequestBody Admin admin){
        Map map = adminService.updateAdmin(admin);
        Integer errno = (Integer) map.get("errno");
        if (errno==1) return BaseReqVo.ok(admin);
        if (errno==2) return new BaseReqVo("管理员密码长度不能小于6位",601);
        return new BaseReqVo("管理员名称长度不能小于6位",601);
    }

    @AdminRecord
    @RequestMapping("delete")
    public BaseReqVo delete(@RequestBody Admin admin){
        int i = adminService.deleteAdmin(admin);
        return BaseReqVo.ok();
    }
}
