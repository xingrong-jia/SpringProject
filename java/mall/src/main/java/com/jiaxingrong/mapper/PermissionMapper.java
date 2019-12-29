package com.jiaxingrong.mapper;

import com.jiaxingrong.model.Permission;
import com.jiaxingrong.model.PermissionExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PermissionMapper {
    long countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<String> selectPermissionByRoleId(@Param("roleIds") Integer[] roleIds, @Param("deleted") boolean deleted);

    int updatePermissionByRoleIdAndPermission(@Param("permission") Permission permission);
}