package com.jiaxingrong.service;

import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.model.Permission;
import com.jiaxingrong.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    List options();

    Map list(Laypage laypage);

    Role addRole(Role role);

    Role updateRole(Role role);

    int deleteRole(Role role);

}
