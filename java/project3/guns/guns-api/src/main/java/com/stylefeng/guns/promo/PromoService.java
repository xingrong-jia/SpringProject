package com.stylefeng.guns.promo;

import com.stylefeng.guns.cinema.vo.CinemasReqVo;
import com.stylefeng.guns.order.vo.OrderRespVo;
import com.stylefeng.guns.promo.vo.PromoReqVo;
import com.stylefeng.guns.promo.vo.PromoRespVo;

import java.util.Collection;
import java.util.List;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-13 8:56
 */
public interface PromoService {
    List<PromoRespVo> getPromos(CinemasReqVo reqVo);

    Integer publishPromoStock(Integer cinemaId);

    String generateToken(Integer promoId,Integer userId);

    Integer createOrder(PromoReqVo reqVo);

    Integer createPromoOrder(Integer userId, Integer promoId, Integer amount);

    Integer updatePromoStock(Integer promoId, Integer amount);

    List<OrderRespVo> getOrderInfo(Integer userId, Integer nowPage, Integer pageSize);
}
