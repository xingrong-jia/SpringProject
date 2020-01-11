package com.stylefeng.guns.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.Year;
import java.util.List;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-09 16:23
 */
@Data
public class FilmConditionRespVo implements Serializable {

    private List<CatVo> catInfo;

    private List<SourceVo> sourceInfo;

    private List<YearVo> yearInfo;
}
