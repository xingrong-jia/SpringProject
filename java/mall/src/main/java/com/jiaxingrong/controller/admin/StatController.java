package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/stat")
public class StatController {

    @Autowired
    StatService statService;

    @RequestMapping("user")
    public BaseReqVo user(){
        Map map = statService.user();
        return BaseReqVo.ok(map);
    }

    @RequestMapping("order")
    public BaseReqVo order(){
        Map map = statService.order();
        return BaseReqVo.ok(map);
    }

    @RequestMapping("goods")
    public BaseReqVo goods(){
        Map map = statService.goods();
        return BaseReqVo.ok(map);
    }
}
