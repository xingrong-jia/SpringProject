package com.jiaxingrong.service;

import com.jiaxingrong.model.Admin;
import com.jiaxingrong.model.AdminPassword;
import com.jiaxingrong.model.InfoData;
import com.jiaxingrong.model.Laypage;

import java.util.Map;

public interface AdminService {
    boolean login(Admin admin);

    InfoData info(String token);

    int changeAdminPassword(AdminPassword adminPassword);

    Map list(Laypage laypage);

    Map addAdmin(Admin admin);

    Map updateAdmin(Admin admin);

    int deleteAdmin(Admin admin);
}
