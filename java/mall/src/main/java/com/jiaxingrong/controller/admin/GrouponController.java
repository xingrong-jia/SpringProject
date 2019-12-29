package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.*;
import com.jiaxingrong.service.GrouponService;
import com.jiaxingrong.service.Groupon_rulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/groupon")
public class GrouponController {

    @Autowired
    GrouponService grouponService;

    @Autowired
    Groupon_rulesService groupon_rulesService;

    @RequestMapping("list")
    public BaseReqVo list(Laypage laypage){
        Map map = groupon_rulesService.list(laypage);
        return BaseReqVo.ok(map);
    }

    @RequestMapping("create")
    public BaseReqVo create(@RequestBody Groupon_rules groupon_rules){
        groupon_rules = groupon_rulesService.addGroupon_rules(groupon_rules);
        return BaseReqVo.ok(groupon_rules);
    }

    @RequestMapping("update")
    public BaseReqVo update(@RequestBody Groupon_rules groupon_rules){
        groupon_rules = groupon_rulesService.updateGroupon_rules(groupon_rules);
        return BaseReqVo.ok(groupon_rules);
    }

    @RequestMapping("delete")
    public BaseReqVo delete(@RequestBody Groupon_rules groupon_rules){
        int i = groupon_rulesService.deleteGroupon_rules(groupon_rules);
        return BaseReqVo.ok();
    }


    @RequestMapping("listRecord")
    public BaseReqVo listRecord(Laypage laypage){
        Map map = grouponService.list(laypage);
        return BaseReqVo.ok(map);
    }
}
