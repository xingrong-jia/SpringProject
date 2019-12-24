package com.jiaxingrong.service;

import com.jiaxingrong.model.Admin;
import com.jiaxingrong.model.AdminPassword;
import com.jiaxingrong.model.InfoData;

public interface AdminService {
    boolean login(Admin admin);

    InfoData info(String token);

    int changeAdminPassword(AdminPassword adminPassword);
}
