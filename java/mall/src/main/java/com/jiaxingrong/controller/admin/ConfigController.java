package com.jiaxingrong.controller.admin;

import com.jiaxingrong.anntation.ConfigRecord;
import com.jiaxingrong.model.AdminConfig;
import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/config")
public class ConfigController {

    @Autowired
    SystemService systemService;

    @RequestMapping(value = "mall",method = RequestMethod.GET)
    public BaseReqVo mall(){
        Map map = systemService.mall(null);
        return BaseReqVo.ok(map);
    }

    @ConfigRecord
    @RequestMapping(value = "mall",method = RequestMethod.POST)
    public BaseReqVo mall(@RequestBody  AdminConfig adminConfig){
        Map map = systemService.mall(adminConfig);
        return BaseReqVo.ok(adminConfig);
    }

    @RequestMapping(value = "express",method = RequestMethod.GET)
    public BaseReqVo express(){
        Map map = systemService.express(null);
        return BaseReqVo.ok(map);
    }
    @ConfigRecord
    @RequestMapping(value = "express",method = RequestMethod.POST)
    public BaseReqVo express(@RequestBody  AdminConfig adminConfig){
        Map map = systemService.express(adminConfig);
        return BaseReqVo.ok(adminConfig);
    }

    @RequestMapping(value = "order",method = RequestMethod.GET)
    public BaseReqVo order(){
        Map map = systemService.order(null);
        return BaseReqVo.ok(map);
    }
    @ConfigRecord
    @RequestMapping(value = "order",method = RequestMethod.POST)
    public BaseReqVo order(@RequestBody  AdminConfig adminConfig){
        Map map = systemService.order(adminConfig);
        return BaseReqVo.ok(adminConfig);
    }

    @RequestMapping(value = "wx",method = RequestMethod.GET)
    public BaseReqVo wx(){
        Map map = systemService.wx(null);
        return BaseReqVo.ok(map);
    }

    @RequestMapping(value = "wx",method = RequestMethod.POST)
    public BaseReqVo wx(@RequestBody  AdminConfig adminConfig){
        Map map = systemService.wx(adminConfig);
        return BaseReqVo.ok(adminConfig);
    }

}
