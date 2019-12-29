package com.jiaxingrong.service;

import com.jiaxingrong.model.AdminConfig;

import java.util.Map;

public interface SystemService {
    Map mall(AdminConfig adminConfig);

    Map express(AdminConfig adminConfig);

    Map order(AdminConfig adminConfig);

    Map wx(AdminConfig adminConfig);
}
