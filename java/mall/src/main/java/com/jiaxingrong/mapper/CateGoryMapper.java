package com.jiaxingrong.mapper;

import com.jiaxingrong.model.CateGory;
import com.jiaxingrong.model.CateGoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CateGoryMapper {
    long countByExample(CateGoryExample example);

    int deleteByExample(CateGoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CateGory record);

    int insertSelective(CateGory record);

    List<CateGory> selectByExample(CateGoryExample example);

    CateGory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CateGory record, @Param("example") CateGoryExample example);

    int updateByExample(@Param("record") CateGory record, @Param("example") CateGoryExample example);

    int updateByPrimaryKeySelective(CateGory record);

    int updateByPrimaryKey(CateGory record);
}