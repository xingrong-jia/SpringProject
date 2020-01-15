package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.MtimePromoOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2020-01-13
 */
public interface MtimePromoOrderMapper extends BaseMapper<MtimePromoOrder> {

    Integer insertPromoOrder(@Param("promoOrder") MtimePromoOrder promoOrder);
}
