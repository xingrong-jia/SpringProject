package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.MtimeUserT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2020-01-08
 */
public interface MtimeUserTMapper extends BaseMapper<MtimeUserT> {

    MtimeUserT selectMtimeUserTByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    Integer selectUuidByUserName(@Param("username") String username);
}
