package com.jiaxingrong.mapper;

import com.jiaxingrong.model.Groupon_rules;
import com.jiaxingrong.model.Groupon_rulesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Groupon_rulesMapper {
    long countByExample(Groupon_rulesExample example);

    int deleteByExample(Groupon_rulesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Groupon_rules record);

    int insertSelective(Groupon_rules record);

    List<Groupon_rules> selectByExample(Groupon_rulesExample example);

    Groupon_rules selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Groupon_rules record, @Param("example") Groupon_rulesExample example);

    int updateByExample(@Param("record") Groupon_rules record, @Param("example") Groupon_rulesExample example);

    int updateByPrimaryKeySelective(Groupon_rules record);

    int updateByPrimaryKey(Groupon_rules record);
}