package com.stylefeng.guns.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-08 21:55
 */
@Data
public class FilmRespInfo implements Serializable {

    /**
     * 首页的banner信息集合
     */
    private List<BannerVo> banners;

    /**
     * 正在热映的电影信息列表
     */
    private FilmVo hotFilms;

    /**
     * 即将上映的电影信息列表
     */
    private FilmVo soonFilms;

    /**
     * 电影票房排行榜列表
     */
    private List<FilmInfo> boxRanking;

    /**
     * 最受欢迎榜单列表
     */
    private List<FilmInfo> expectRanking;

    /**
     * Top100电影列表
     */
    private List<FilmInfo> top100;
}
