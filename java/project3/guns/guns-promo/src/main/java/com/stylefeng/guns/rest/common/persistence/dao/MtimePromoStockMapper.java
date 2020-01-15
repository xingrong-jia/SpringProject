package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.MtimePromoStock;
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
public interface MtimePromoStockMapper extends BaseMapper<MtimePromoStock> {

    Integer selectStockByPromoId(@Param("promoId") Integer promoId);

    Integer updateStockByPromoId(@Param("promoId") Integer promoId, @Param("amount") Integer amount);
}
