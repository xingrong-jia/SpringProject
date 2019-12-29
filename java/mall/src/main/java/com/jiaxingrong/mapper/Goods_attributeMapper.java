package com.jiaxingrong.mapper;

import com.jiaxingrong.model.Goods_attribute;
import com.jiaxingrong.model.Goods_attributeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface Goods_attributeMapper {
    long countByExample(Goods_attributeExample example);

    int deleteByExample(Goods_attributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods_attribute record);

    int insertSelective(Goods_attribute record);

    List<Goods_attribute> selectByExample(Goods_attributeExample example);

    Goods_attribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goods_attribute record, @Param("example") Goods_attributeExample example);

    int updateByExample(@Param("record") Goods_attribute record, @Param("example") Goods_attributeExample example);

    int updateByPrimaryKeySelective(Goods_attribute record);

    int updateByPrimaryKey(Goods_attribute record);

    int updateDeletdByGoodsId(@Param("goods_id") Integer goods_id, @Param("deleted") boolean deleted);
}