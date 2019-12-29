package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/log")
public class LogController  {

    @Autowired
    LogService logService;

    @RequestMapping("list")
    public BaseReqVo list(Laypage laypage){
        Map map = logService.list(laypage);
        return BaseReqVo.ok(map);
    }
}
