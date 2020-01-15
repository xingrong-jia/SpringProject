package com.stylefeng.guns.film;

import com.stylefeng.guns.film.vo.*;

import java.util.List;
import java.util.Map;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-08 21:14
 */
public interface FilmService {
    Map getIndex();


    FilmConditionRespVo getConditionList(FilmConditionVo filmConditionVo);

    List<FilmInfo> getFilms(FilmReqVo reqVo);

    FilmDetailsVo getFilmDetails(String idOrName);

    String quereFilmNameByFilmId(Integer film_id);
}
