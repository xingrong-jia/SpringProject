package com.jiaxingrong.service;

import com.jiaxingrong.model.Groupon;
import com.jiaxingrong.model.Laypage;

import java.util.Map;

public interface GrouponService {
    Map list(Laypage laypage);

    Groupon addGroupon(Groupon groupon);

    Groupon updateGroupon(Groupon groupon);

    int deleteGroupon(Groupon groupon);
}
