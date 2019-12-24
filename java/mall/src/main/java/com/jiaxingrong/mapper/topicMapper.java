package com.jiaxingrong.mapper;

import com.jiaxingrong.model.topic;
import com.jiaxingrong.model.topicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface topicMapper {
    long countByExample(topicExample example);

    int deleteByExample(topicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(topic record);

    int insertSelective(topic record);

    List<topic> selectByExampleWithBLOBs(topicExample example);

    List<topic> selectByExample(topicExample example);

    topic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") topic record, @Param("example") topicExample example);

    int updateByExampleWithBLOBs(@Param("record") topic record, @Param("example") topicExample example);

    int updateByExample(@Param("record") topic record, @Param("example") topicExample example);

    int updateByPrimaryKeySelective(topic record);

    int updateByPrimaryKeyWithBLOBs(topic record);

    int updateByPrimaryKey(topic record);
}