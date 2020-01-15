package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.MtimeFieldT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2020-01-10
 */
public interface MtimeFieldTMapper extends BaseMapper<MtimeFieldT> {

    MtimeFieldT selectMtimeFieldTByCinemaIdAndFieldId(@Param("cinemaId") Integer cinemaId, @Param("fieldId") Integer fieldId);
}
