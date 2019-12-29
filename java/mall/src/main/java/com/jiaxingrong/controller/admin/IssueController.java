package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.Issue;
import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/issue")
public class IssueController {


    @Autowired
    IssueService issueService;

    @RequestMapping("list")
    public BaseReqVo issue(Laypage lazypage){
        Map<String,Object> issues = issueService.list(lazypage);
        return new BaseReqVo<>(issues,"成功",0);
    }


    @RequestMapping("create")
    public BaseReqVo createIssue(@RequestBody Issue issue){
        issue = issueService.addIssue(issue);
        return new BaseReqVo<>(issue,"成功",0);
    }

    @RequestMapping("update")
    public BaseReqVo updateIssue(@RequestBody Issue issue){
        issue = issueService.updateIssue(issue);
        return new BaseReqVo<>(issue,"成功",0);
    }

    @RequestMapping("delete")
    public BaseReqVo deleteIssue(@RequestBody Issue issue){
        issue = issueService.deleteIssue(issue);
        return new BaseReqVo<>("成功",0);
    }

}
