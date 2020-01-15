package com.stylefeng.guns.cinema;

import com.stylefeng.guns.cinema.vo.CinemaConditionRespVo;
import com.stylefeng.guns.cinema.vo.CinemasReqVo;
import com.stylefeng.guns.cinema.vo.FieldInfo;
import com.stylefeng.guns.order.vo.OrderField;
import com.stylefeng.guns.promo.vo.PromoCinemaVo;
import com.stylefeng.guns.promo.vo.PromoRespVo;

import java.util.Map;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-10 15:16
 */
public interface CinemaService {
    Map<String, Object> getCinemas(CinemasReqVo cinemasReqVo);

    CinemaConditionRespVo getCondition(CinemasReqVo cinemasReqVo);

    Map<String, Object> getFields(Integer cinemaId);

    FieldInfo getFieldInfo(Integer cinemaId, Integer fieldId,String soldSeats);

    OrderField queryFieldHallNameByFiledId(Integer filedId);

    String querySeat_addressByShow_name(String hallName);

    String queryCinemaNameByCinemaId(Integer cinema_id);

    String queryseat_addressByHallId(Integer hall_id);

    String queryFilmNameByFilmName(Integer filmId);

    PromoRespVo queryPromoCinemaVoBycinemaId(Integer cinemaId);
}
