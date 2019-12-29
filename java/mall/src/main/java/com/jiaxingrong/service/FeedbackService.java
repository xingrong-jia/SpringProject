package com.jiaxingrong.service;

import com.jiaxingrong.model.Laypage;

import java.util.Map;

public interface FeedbackService {
    Map<String, Object> list(Laypage laypage);
}
