package com.jiaxingrong.service;

import com.jiaxingrong.model.Issue;
import com.jiaxingrong.model.Laypage;

import java.util.List;
import java.util.Map;

public interface IssueService {
    Map<String,Object> list(Laypage laypage);

    Issue addIssue(Issue issue);

    Issue updateIssue(Issue issue);

    Issue deleteIssue(Issue issue);
}
