package com.jiaxingrong.service;

import com.jiaxingrong.model.Laypage;

import java.util.Map;

public interface CollectService {
    Map<String, Object> list(Laypage laypage);
}
