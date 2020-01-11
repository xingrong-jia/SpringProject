package com.stylefeng.guns.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-09 16:09
 */
@Data
public class FilmConditionVo implements Serializable {

    private Integer catId;

    private Integer sourceId;

    private Integer yearId;
}
