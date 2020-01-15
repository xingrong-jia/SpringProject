package com.stylefeng.guns.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-10 16:51
 */
@Data
public class BrandVo implements Serializable {

    private Boolean active;

    private Integer brandId;

    private String brandName;
}
