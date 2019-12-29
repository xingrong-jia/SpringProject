package com.jiaxingrong.shiro;


import com.jiaxingrong.mapper.AdminMapper;
import com.jiaxingrong.mapper.PermissionMapper;
import com.jiaxingrong.model.Admin;
import com.jiaxingrong.model.AdminExample;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();

        //Admin admin = adminMapper.selectAdminByuserName(username);
        AdminExample example = new AdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<Admin> admins = adminMapper.selectByExample(example);
        Admin admin = admins.get(0);
        String passwordFromDb = admin.getPassword();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(admin, passwordFromDb, getName());

        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Admin admin = (Admin) principalCollection.getPrimaryPrincipal();
        String username = admin.getUsername();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //String roleIds = primaryPrincipal.getRoleIds();
        //Integer[] integers = JsonUtils.convertToObject(roleIds, Integer[].class);
        List<String> permissions = permissionMapper.selectPermissionByRoleId(admin.getRoleIds(),false);

        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

}
