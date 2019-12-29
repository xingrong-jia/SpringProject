package com.jiaxingrong.service;

import com.jiaxingrong.model.Groupon_rules;
import com.jiaxingrong.model.Laypage;

import java.util.Map;

public interface Groupon_rulesService {
    Map list(Laypage laypage);

    Groupon_rules addGroupon_rules(Groupon_rules groupon_rules);

    Groupon_rules updateGroupon_rules(Groupon_rules groupon_rules);

    int deleteGroupon_rules(Groupon_rules groupon_rules);

}
