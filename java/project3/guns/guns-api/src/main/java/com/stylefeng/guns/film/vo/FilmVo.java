package com.stylefeng.guns.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-08 21:50
 */
@Data
public class FilmVo implements Serializable {

    private Integer filmNum;
    private List<FilmInfo> filmInfo;

}
