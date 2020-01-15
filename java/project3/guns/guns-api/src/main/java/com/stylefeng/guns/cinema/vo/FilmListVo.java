package com.stylefeng.guns.cinema.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-10 17:36
 */
@Data
public class FilmListVo implements Serializable {

    private String actors;

    private String filmCats;

    private List<FilmFields> filmFields;

    private Integer filmId;

    private String filmLength;

    private String filmName;

    private String filmType;

    private String imgAddress;

}
