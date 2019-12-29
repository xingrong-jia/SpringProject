package com.jiaxingrong.mapper;

import com.jiaxingrong.model.SystemPermission;
import com.jiaxingrong.model.SystemPermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemPermissionMapper {
    long countByExample(SystemPermissionExample example);

    int deleteByExample(SystemPermissionExample example);

    int deleteByPrimaryKey(Integer primaryId);

    int insert(SystemPermission record);

    int insertSelective(SystemPermission record);

    List<SystemPermission> selectByExample(SystemPermissionExample example);

    SystemPermission selectByPrimaryKey(Integer primaryId);

    int updateByExampleSelective(@Param("record") SystemPermission record, @Param("example") SystemPermissionExample example);

    int updateByExample(@Param("record") SystemPermission record, @Param("example") SystemPermissionExample example);

    int updateByPrimaryKeySelective(SystemPermission record);

    int updateByPrimaryKey(SystemPermission record);

    String selectApiById(@Param("id") String id);
}