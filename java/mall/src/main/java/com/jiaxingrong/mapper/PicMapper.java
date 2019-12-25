package com.jiaxingrong.mapper;

import com.jiaxingrong.model.Pic;
import org.apache.ibatis.annotations.Param;

public interface PicMapper {
    int insertPic(@Param("pic") Pic pic);
}
