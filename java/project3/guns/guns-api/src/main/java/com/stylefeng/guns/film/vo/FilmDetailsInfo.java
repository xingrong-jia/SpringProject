package com.stylefeng.guns.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-09 18:23
 */
@Data
public class FilmDetailsInfo implements Serializable {

    private String biography;

    private FilmActorsVo actors;

    private Map<String,String> imgVO;

}
