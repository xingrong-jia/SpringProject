package com.jiaxingrong.service;

import com.jiaxingrong.model.Laypage;

import java.util.Map;

public interface OrderService {
    Map<String, Object> list(Laypage laypage);
}
