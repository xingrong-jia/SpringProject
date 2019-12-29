package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.*;
import com.jiaxingrong.service.*;
import com.jiaxingrong.utils.StringTool;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @RequiresPermissions(value = "admin:user:list")
    public BaseReqVo<Map> userList(Laypage laypage){
        Map<String,Object> map = userService.list(laypage);
        return new BaseReqVo<>(map,"成功",0);
    }

    @RequestMapping("address/list")
    @RequiresPermissions("admin:address:list")
    public BaseReqVo<Map> addressList(Laypage laypage) throws Exception{
        Map<String,Object> map = addressService.list(laypage);
        return new BaseReqVo<>(map,"成功",0);
    }

    @RequestMapping("collect/list")
    @RequiresPermissions("admin:collect:list")
    public BaseReqVo<Map> collectList(Laypage laypage){
        Map<String,Object> map = collectService.list(laypage);
        return new BaseReqVo<>(map,"成功",0);
    }

    @RequestMapping("footprint/list")
    @RequiresPermissions("admin:footprint:list")
    public BaseReqVo<Map> footprintList(Laypage laypage){
        Map<String,Object> map = footprintService.list(laypage);
        return new BaseReqVo<>(map,"成功",0);
    }

    @RequestMapping("history/list")
    @RequiresPermissions("admin:history:list")
    public BaseReqVo<Map> historyList(Laypage laypage){
        Map<String,Object> map = search_historyService.list(laypage);
        return new BaseReqVo<>(map,"成功",0);
    }

    @RequestMapping("feedback/list")
    @RequiresPermissions("admin:feedback:list")
    public BaseReqVo<Map> feedbackList(Laypage laypage){
        Map<String,Object> map = feedbackService.list(laypage);
        return new BaseReqVo<>(map,"成功",0);
    }

    private boolean isNumber(String s){
        if (StringTool.isNotNull(s)&&StringTool.isNumber(s)){
            return false;
        }
        return true;
    }
}
