package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.Dashboard;
import com.jiaxingrong.service.DashboardService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AashboardController {

    @Autowired
    DashboardService dashboardService;

    @RequestMapping("dashboard")
    @RequiresPermissions(value = "admin:dashboard")
    public BaseReqVo dashboard(){
        Dashboard dashboard = dashboardService.dashboard();
        BaseReqVo<Dashboard> baseReqVo = new BaseReqVo<>();
        baseReqVo.setData(dashboard);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
