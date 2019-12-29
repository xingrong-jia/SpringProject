package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.*;
import com.jiaxingrong.service.PermissionService;
import com.jiaxingrong.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/role")
public class RoleController  {

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;

    @RequestMapping("options")
    public BaseReqVo options(){
        List list = roleService.options();
        return BaseReqVo.ok(list);
    }

    @RequestMapping("list")
    public BaseReqVo list(Laypage laypage){
        Map map = roleService.list(laypage);
        return BaseReqVo.ok(map);
    }


    @RequestMapping("create")
    public BaseReqVo create(@RequestBody Role role){
        role = roleService.addRole(role);
        return BaseReqVo.ok(role);
    }

    @RequestMapping("update")
    public BaseReqVo update(@RequestBody Role role){
        role = roleService.updateRole(role);
        return BaseReqVo.ok(role);
    }

    @RequestMapping("delete")
    public BaseReqVo delete(@RequestBody Role role){
        int i = roleService.deleteRole(role);
        return BaseReqVo.ok();
    }

    @RequestMapping(value = "permissions",method = RequestMethod.GET)
    public BaseReqVo permissions(Integer roleId){
        Map map = permissionService.permissions(roleId);
        return BaseReqVo.ok(map);
    }

    @RequestMapping(value = "permissions",method = RequestMethod.POST)
    public BaseReqVo permissions2(@RequestBody  Permission permission){
        permissionService.addPermissions(permission);
        return BaseReqVo.ok();
    }
}
