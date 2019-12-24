package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.*;
import com.jiaxingrong.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @Autowired
    CollectService collectService;

    @Autowired
    FootprintService footprintService;

    @Autowired
    Search_historyService search_historyService;

    @Autowired
    FeedbackService feedbackService;

    @RequestMapping("user/list")
    public BaseReqVo<Map> userList(Laypage laypage){
        Map<String,Object> map = userService.list(laypage);
        return getBaseReqVo(map);
    }

    @RequestMapping("address/list")
    public BaseReqVo<Map> addressList(Laypage laypage){
        Map<String,Object> map = addressService.list(laypage);
        return getBaseReqVo(map);
    }

    @RequestMapping("collect/list")
    public BaseReqVo<Map> collectList(Laypage laypage){
        Map<String,Object> map = collectService.list(laypage);
        return getBaseReqVo(map);
    }

    @RequestMapping("footprint/list")
    public BaseReqVo<Map> footprintList(Laypage laypage){
        Map<String,Object> map = footprintService.list(laypage);
        return getBaseReqVo(map);
    }

    @RequestMapping("history/list")
    public BaseReqVo<Map> historyList(Laypage laypage){
        Map<String,Object> map = search_historyService.list(laypage);
        return getBaseReqVo(map);
    }

    @RequestMapping("feedback/list")
    public BaseReqVo<Map> feedbackList(Laypage laypage){
        Map<String,Object> map = feedbackService.list(laypage);
        return getBaseReqVo(map);
    }

    private BaseReqVo<Map> getBaseReqVo(Map map){
        BaseReqVo<Map> baseReqVo = new BaseReqVo<>(map,"成功",0);
        return baseReqVo;
    }
}
