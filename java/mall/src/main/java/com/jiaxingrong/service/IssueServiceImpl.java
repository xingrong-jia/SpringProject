package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.IssueMapper;
import com.jiaxingrong.model.*;
import com.jiaxingrong.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    IssueMapper issueMapper;

    @Override
    public Map<String,Object> list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        IssueExample issueExample = new IssueExample();
        issueExample.setOrderByClause(laypage.getSort()+" "+laypage.getOrder());
        IssueExample.Criteria criteria = issueExample.createCriteria();
        if (StringTool.isNotNull(laypage.getQuestion())) {
            criteria.andQuestionLike("%" + laypage.getQuestion() + "%");
        }
        criteria.andDeletedEqualTo(false);
        List<Issue> issues = issueMapper.selectByExample(issueExample);
        PageInfo<Issue> issuePageInfo = new PageInfo<>(issues);
        long total = issuePageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", issues);
        return map;
    }

    /**
     * 添加通用问题
     * @param issue
     * @return
     */
    @Override
    public Issue addIssue(Issue issue) {
        issue.setAddTime(new Date());
        issue.setUpdateTime(new Date());
        issue.setDeleted(false);
        issueMapper.insertSelective(issue);
        return issue;
    }

    /**
     * 修改问题信息
     * @param issue
     * @return
     */
    @Override
    public Issue updateIssue(Issue issue) {
        issue.setUpdateTime(new Date());
        issueMapper.updateByPrimaryKeySelective(issue);
        return issue;
    }

    /**
     * 删除问题 即将问题的deleteId变为true
     * @param issue
     * @return
     */
    @Override
    public Issue deleteIssue(Issue issue) {
        issue.setUpdateTime(new Date());
        issue.setDeleted(true);
        issueMapper.updateByPrimaryKeySelective(issue);
        return issue;
    }
}
