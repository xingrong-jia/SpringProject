package com.jiaxingrong.shiro;


import com.jiaxingrong.mapper.UserMapper;
import com.jiaxingrong.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WxRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();

        User user = userMapper.selectUserByName(username);
        String passwordFromDb = user.getPassword();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, passwordFromDb, getName());

        return authenticationInfo;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*User primaryPrincipal = (User) principalCollection.getPrimaryPrincipal();
        String username = primaryPrincipal.getUsername();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();*/
        //List<String> permissions = userMapper.selectPermissionByUsername(username);

// //       authorizationInfo.addStringPermission("user:query");
        //authorizationInfo.addStringPermissions(permissions);
        //return authorizationInfo;
        return null;
    }

}
