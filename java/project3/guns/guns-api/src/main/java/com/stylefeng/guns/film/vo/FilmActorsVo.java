package com.stylefeng.guns.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-09 18:26
 */
@Data
public class FilmActorsVo implements Serializable {

    private List<FilmActorsVo> actors;

    private String directorName;

    private String imgAddress;

    private String roleName;

    private FilmActorsVo director;

}
