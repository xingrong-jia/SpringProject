package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.MtimeHallDictT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 地域信息表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2020-01-10
 */
public interface MtimeHallDictTMapper extends BaseMapper<MtimeHallDictT> {

    MtimeHallDictT selectMtimeHallDictTByHallName(@Param("hallName") String hallName);

    String selectSeat_addressByShow_name(@Param("hallName") String hallName);
}
