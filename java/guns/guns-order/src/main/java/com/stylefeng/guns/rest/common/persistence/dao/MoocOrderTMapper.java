package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.MoocOrderT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单信息表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2020-01-11
 */
public interface MoocOrderTMapper extends BaseMapper<MoocOrderT> {

    List<String> selectSeats_idsByField_id(@Param("filedId") Integer filedId);

    MoocOrderT selectOrderByOrderId(@Param("orderId") String orderId);
}
