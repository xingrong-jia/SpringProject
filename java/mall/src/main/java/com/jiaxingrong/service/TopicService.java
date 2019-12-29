package com.jiaxingrong.service;

import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.model.Topic;

import java.util.Map;

public interface TopicService {
    Map list(Laypage laypage);

    Topic addTopic(Topic topic);

    Topic updateTopic(Topic topic);

    int deleteTopic(Topic topic);
}
