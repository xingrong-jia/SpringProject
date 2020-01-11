package com.stylefeng.guns.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-08 21:41
 */
@Data
public class BannerVo implements Serializable {

    private String bannerId;

    private String bannerAddress;

    private String bannerUrl;

}
