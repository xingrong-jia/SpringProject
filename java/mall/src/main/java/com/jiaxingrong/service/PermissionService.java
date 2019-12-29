package com.jiaxingrong.service;

import com.jiaxingrong.model.Permission;

import java.util.Map;

public interface PermissionService {
    Map permissions(Integer roleId);

    int addPermissions(Permission permission);
}
