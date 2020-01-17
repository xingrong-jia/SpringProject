package com.stylefeng.guns.rest.common.persistence.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jiaxingrong.utils.CollectionUtils;
import com.jiaxingrong.utils.JsonUtils;
import com.jiaxingrong.utils.StringTool;
import com.stylefeng.guns.film.FilmService;
import com.stylefeng.guns.film.vo.*;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-08 21:13
 */
@Slf4j
@Component
@Service(interfaceClass = FilmService.class,retries = 1)
public class FilmServiceImpl implements FilmService {

    @Autowired
    MtimeBannerTMapper bannerTMapper;

    @Autowired
    MtimeFilmTMapper filmTMapper;

    @Autowired
    MtimeFilmInfoTMapper filmInfoTMapper;

    @Autowired
    MtimeCatDictTMapper catDictTMapper;

    @Autowired
    MtimeSourceDictTMapper sourceDictTMapper;

    @Autowired
    MtimeYearDictTMapper yearDictTMapper;

    @Autowired
    MtimeFilmActorTMapper actorTMapper;

    @Autowired
    MtimeActorTMapper actorMapper;

    @Autowired
    GuavaCacheService cacheService;

    @Autowired
    RedisTemplate redisTemplate;

    private static final String GET_INDEX = "get_index";

    @Override
    public  Map getIndex() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("imgPre", "http://img.meetingshop.cn/");
        String s = (String) cacheService.get(GET_INDEX);
        if (s!=null) {
            log.info("从本地缓存中读取数据");
            FilmRespInfo filmRespInfo = JsonUtils.convertToObject(s, FilmRespInfo.class);
            map.put("data", filmRespInfo);
            return map;
        }

        log.info("本地缓存无数据");
        String reidsData = (String) redisTemplate.opsForValue().get(GET_INDEX);
        if (reidsData!=null){
            log.info("从redis缓存中读取数据");
            FilmRespInfo filmRespInfo = JsonUtils.convertToObject(reidsData, FilmRespInfo.class);
            map.put("data", filmRespInfo);
            cacheService.set(GET_INDEX,reidsData);
            log.info("本地缓存写入数据成功");
            return map;
        }
        log.info("redis缓存无数据");
        FilmRespInfo filmRespInfo = new FilmRespInfo();

        ArrayList<BannerVo> bannerVos = getBanners();
        filmRespInfo.setBanners(bannerVos);
        //热映影片
        FilmVo hotFilms = getHotFilms();
        filmRespInfo.setHotFilms(hotFilms);

        FilmVo soonFilms = getSoonFilms();
        filmRespInfo.setSoonFilms(soonFilms);

        List<FilmInfo> boxRanking = getBoxRanking();
        filmRespInfo.setBoxRanking(boxRanking);

        List<FilmInfo> expectRanking = getExpectRanking();
        filmRespInfo.setExpectRanking(expectRanking);

        List<FilmInfo> top100 = getTop100();
        filmRespInfo.setTop100(top100);

        map.put("data", filmRespInfo);
        String toJson = JsonUtils.convertToJson(filmRespInfo);
        redisTemplate.opsForValue().set(GET_INDEX,toJson,30);
        log.info("本地缓存写入数据成功");
        cacheService.set(GET_INDEX,toJson);
        log.info("redis缓存写入数据成功");
        return map;
    }

    @Override
    public FilmConditionRespVo getConditionList(FilmConditionVo filmConditionVo) {
        FilmConditionRespVo conditionRespVo = new FilmConditionRespVo();

        List<CatVo> catVos = getCatInfo(filmConditionVo.getCatId());
        conditionRespVo.setCatInfo(catVos);

        List<SourceVo> sourceVos = getSourceInfo(filmConditionVo.getSourceId());
        conditionRespVo.setSourceInfo(sourceVos);

        List<YearVo> yearVos = getYearInfo(filmConditionVo.getYearId());
        conditionRespVo.setYearInfo(yearVos);

        return conditionRespVo;
    }

    @Override
    public List<FilmInfo> getFilms(FilmReqVo reqVo) {
        Wrapper<MtimeFilmT> filmTWrapper = new EntityWrapper<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("film_status", reqVo.getShowType());
        if (reqVo.getCatId() != 99) {
            filmTWrapper.like("film_cats", "#" + reqVo.getCatId() + "#");
        }
        if (reqVo.getSourceId() != 99) {
            map.put("film_area", reqVo.getSourceId());
        }
        if (reqVo.getYearId() != 99) {
            map.put("film_date", reqVo.getYearId());
        }
        filmTWrapper.allEq(map);
        if (reqVo.getSortId() == 1) {
            filmTWrapper.orderBy("film_box_office", false);
        }
        if (reqVo.getSortId() == 2) {
            filmTWrapper.orderBy("film_time", false);
        }
        if (reqVo.getSortId() == 3) {
            filmTWrapper.orderBy("film_score", false);
        }
        RowBounds rowBounds = new RowBounds(reqVo.getNowPage(), reqVo.getPageSize());

        List<MtimeFilmT> filmTS = filmTMapper.selectPage(rowBounds, filmTWrapper);

        ArrayList<FilmInfo> filmInfos = new ArrayList<>();
        for (MtimeFilmT mtimeFilmT : filmTS) {
            FilmInfo filmInfo = new FilmInfo();
            filmInfo.setFilmId(mtimeFilmT.getUuid().toString());
            filmInfo.setFilmType(mtimeFilmT.getFilmType());
            filmInfo.setImgAddress(mtimeFilmT.getImgAddress());
            filmInfo.setFilmName(mtimeFilmT.getFilmName());
            filmInfo.setScore(mtimeFilmT.getFilmScore());
            filmInfos.add(filmInfo);
        }
        return filmInfos;
    }

    @Override
    public FilmDetailsVo getFilmDetails(String idOrName) {
        FilmDetailsVo filmDetailsVo = new FilmDetailsVo();
        Wrapper<MtimeFilmInfoT> infoTWrapper = new EntityWrapper<>();
        MtimeFilmT mtimeFilmT = null;
        if (StringTool.isNumber(idOrName)) {
            int filmId = Integer.parseInt(idOrName);
            mtimeFilmT = filmTMapper.selectById(filmId);

        } else {
            Wrapper<MtimeFilmT> filmTWrapper = new EntityWrapper<>();
            filmTWrapper.eq("film_name", idOrName);
            List<MtimeFilmT> mtimeFilmTS = filmTMapper.selectList(filmTWrapper);
            if (mtimeFilmTS == null || mtimeFilmTS.size() == 0 || mtimeFilmTS.size() > 1) return filmDetailsVo;
            mtimeFilmT = mtimeFilmTS.get(0);
        }
        if (mtimeFilmT == null) return filmDetailsVo;
        filmDetailsVo.setFilmId(mtimeFilmT.getUuid().toString());
        filmDetailsVo.setFilmName(mtimeFilmT.getFilmName());
        infoTWrapper.eq("film_id", mtimeFilmT.getUuid().toString());
        List<MtimeFilmInfoT> infoTS = filmInfoTMapper.selectList(infoTWrapper);
        Integer filmSource = mtimeFilmT.getFilmSource();
        MtimeSourceDictT sourceDictT = sourceDictTMapper.selectById(filmSource);
        Integer director_id = 0;
        String biopgraphy = "";
        String imgVoStr = "";
        if (infoTS.size() == 1) {
            MtimeFilmInfoT filmInfoT = infoTS.get(0);
            filmDetailsVo.setFilmEnName(filmInfoT.getFilmEnName());
            filmDetailsVo.setScoreNum(filmInfoT.getFilmScoreNum().toString() + "万人评分");

            director_id = filmInfoT.getDirectorId();
            biopgraphy = filmInfoT.getBiography();
            imgVoStr = filmInfoT.getFilmImgs();
            filmDetailsVo.setInfo02(sourceDictT.getShowName() + "/" + filmInfoT.getFilmLength() + "分钟");
        }
        filmDetailsVo.setImgAddress(mtimeFilmT.getImgAddress());
        filmDetailsVo.setScore(mtimeFilmT.getFilmScore());
        Double boxOffice = mtimeFilmT.getFilmBoxOffice().doubleValue();
        boxOffice = boxOffice / 10000.0;
        filmDetailsVo.setTotalBox(boxOffice.toString() + "亿");
        String filmCats = mtimeFilmT.getFilmCats();
        String info01 = getfilmCats(filmCats);
        filmDetailsVo.setInfo01(info01);
        filmDetailsVo.setInfo03(StringTool.date2String(mtimeFilmT.getFilmTime()) + " " + sourceDictT.getShowName() + "上映");
        FilmDetailsInfo info04 = new FilmDetailsInfo();
        Wrapper<MtimeFilmActorT> actorTWrapper = new EntityWrapper<>();
        actorTWrapper.eq("film_id", mtimeFilmT.getUuid());
        FilmActorsVo actorss = new FilmActorsVo();
        List<MtimeFilmActorT> actorTS = actorTMapper.selectList(actorTWrapper);
        ArrayList<FilmActorsVo> actors = new ArrayList<>();
        for (MtimeFilmActorT actorT : actorTS) {
            FilmActorsVo filmActorsVo = new FilmActorsVo();
            Integer actorId = actorT.getActorId();
            MtimeActorT actorT1 = actorMapper.selectById(actorId);
            filmActorsVo.setDirectorName(actorT1.getActorName());
            filmActorsVo.setImgAddress(actorT1.getActorImg());
            filmActorsVo.setRoleName(actorT.getRoleName());
            actors.add(filmActorsVo);
        }
        actorss.setActors(actors);
        FilmActorsVo filmActorsVo = new FilmActorsVo();
        MtimeActorT t = actorMapper.selectById(director_id);
        filmActorsVo.setDirectorName(t.getActorName());
        filmActorsVo.setImgAddress(t.getActorImg());
        actorss.setDirector(filmActorsVo);
        info04.setActors(actorss);
        info04.setBiography(biopgraphy);
        Map<String, String> imgVo = getImgVo(imgVoStr);
        info04.setImgVO(imgVo);
        filmDetailsVo.setInfo04(info04);
        return filmDetailsVo;
    }

    @Override
    public String quereFilmNameByFilmId(Integer film_id) {
        MtimeFilmT mtimeFilmT = filmTMapper.selectById(film_id);
        if (mtimeFilmT != null) return mtimeFilmT.getFilmName();
        return null;
    }

    private Map<String, String> getImgVo(String imgVoStr) {
        String[] replace = imgVoStr.split(",");
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < replace.length; i++) {
            if (i == 0) {
                map.put("mainImg", replace[i]);
            }
            if (i > 0 && i < 10) {
                map.put("img0" + i, replace[i]);
            } else {
                map.put("img" + i, replace[i]);
            }
        }
        return map;
    }

    private String getfilmCats(String filmCats) {
        String[] replace = filmCats.split("#");
        StringBuffer sb = new StringBuffer();
        for (String s : replace) {
            if (StringTool.isNumber(s)) {
                MtimeCatDictT catDictT = catDictTMapper.selectById(s);
                sb.append(catDictT.getShowName()).append(",");
            }
        }
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private List<YearVo> getYearInfo(Integer yearId) {
        List<MtimeYearDictT> yearDictTS = yearDictTMapper.selectList(new EntityWrapper<>());
        ArrayList<YearVo> yearVos = new ArrayList<>();
        for (MtimeYearDictT mtimeYearDictT : yearDictTS) {
            YearVo yearVo = new YearVo();
            yearVo.setYearId(mtimeYearDictT.getUuid().toString());
            yearVo.setYearName(mtimeYearDictT.getShowName());
            if (mtimeYearDictT.getUuid().equals(yearId)) {
                yearVo.setIsActive(true);
            } else {
                yearVo.setIsActive(false);
            }
            yearVos.add(yearVo);
        }
        return yearVos;
    }

    private List<SourceVo> getSourceInfo(Integer sourceId) {
        List<MtimeSourceDictT> sourceDictTS = sourceDictTMapper.selectList(new EntityWrapper<>());
        ArrayList<SourceVo> sourceVos = new ArrayList<>();
        for (MtimeSourceDictT sourceDictT : sourceDictTS) {
            SourceVo sourceVo = new SourceVo();
            sourceVo.setSourceId(sourceDictT.getUuid().toString());
            sourceVo.setSourceName(sourceDictT.getShowName());
            if (sourceDictT.getUuid().equals(sourceId)) {
                sourceVo.setIsActive(true);
            } else {
                sourceVo.setIsActive(false);
            }
            sourceVos.add(sourceVo);
        }
        return sourceVos;
    }

    private List<CatVo> getCatInfo(Integer catId) {
        List<MtimeCatDictT> catDictTS = catDictTMapper.selectList(new EntityWrapper<>());
        ArrayList<CatVo> catVos = new ArrayList<>();
        for (MtimeCatDictT catDictT : catDictTS) {
            CatVo catVo = new CatVo();
            catVo.setCatId(catDictT.getUuid().toString());
            catVo.setCatName(catDictT.getShowName());
            if (catDictT.getUuid().equals(catId)) {
                catVo.setIsActive(true);
            } else {
                catVo.setIsActive(false);
            }
            catVos.add(catVo);
        }
        return catVos;
    }

    /**
     * 获得top100的电影列表
     *
     * @return
     */
    private List<FilmInfo> getTop100() {
        //PageHelper.startPage(1, 10);
        RowBounds rowBounds = new RowBounds(0, 10);
        List<FilmInfo> top100 = new ArrayList<>();
        Wrapper<MtimeFilmT> filmTWrapper = new EntityWrapper<>();
        filmTWrapper.orderBy("film_score", false);
        List<MtimeFilmT> filmTS = filmTMapper.selectPage(rowBounds, filmTWrapper);
        for (MtimeFilmT mtimeFilmT : filmTS) {
            FilmInfo filmInfo = new FilmInfo();
            filmInfo.setFilmId(mtimeFilmT.getUuid().toString());
            filmInfo.setImgAddress(mtimeFilmT.getImgAddress());
            filmInfo.setFilmName(mtimeFilmT.getFilmName());
            filmInfo.setScore(mtimeFilmT.getFilmScore());
            top100.add(filmInfo);
        }
        return top100;
    }

    /**
     * 获得最受欢迎榜单列表
     *
     * @return
     */
    private List<FilmInfo> getExpectRanking() {
        //PageHelper.startPage(1, 10);
        RowBounds rowBounds = new RowBounds(0, 10);
        List<FilmInfo> expectRanking = new ArrayList<>();
        Wrapper<MtimeFilmInfoT> filmInfoTWrapper = new EntityWrapper<>();
        filmInfoTWrapper.orderBy("film_score_num", false);
        List<MtimeFilmInfoT> filmInfoTS = filmInfoTMapper.selectPage(rowBounds, filmInfoTWrapper);
        for (MtimeFilmInfoT mtimeFilmInfoT : filmInfoTS) {
            FilmInfo filmInfo = new FilmInfo();
            String filmId = mtimeFilmInfoT.getFilmId();
            filmInfo.setFilmId(filmId);
            MtimeFilmT mtimeFilmT = filmTMapper.selectById(filmId);
            filmInfo.setImgAddress(mtimeFilmT.getImgAddress());
            filmInfo.setFilmName(mtimeFilmT.getFilmName());
            int i = mtimeFilmInfoT.getFilmScoreNum() * 10000;
            i += Math.random() * 5000;
            filmInfo.setExpectNum(i);
            expectRanking.add(filmInfo);
        }
        return expectRanking;
    }

    /**
     * 获得电影票房排行榜
     *
     * @return
     */
    private List<FilmInfo> getBoxRanking() {
        //PageHelper.startPage(1, 10);
        RowBounds rowBounds = new RowBounds(0, 10);
        List<FilmInfo> boxRanking = new ArrayList<>();
        Wrapper<MtimeFilmT> filmTWrapper = new EntityWrapper<>();
        filmTWrapper.orderBy("film_box_office", false);
        List<MtimeFilmT> filmTS = filmTMapper.selectPage(rowBounds, filmTWrapper);
        for (MtimeFilmT mtimeFilmT : filmTS) {
            FilmInfo filmInfo = new FilmInfo();
            filmInfo.setFilmId(mtimeFilmT.getUuid().toString());
            filmInfo.setImgAddress(mtimeFilmT.getImgAddress());
            filmInfo.setFilmName(mtimeFilmT.getFilmName());
            filmInfo.setBoxNum(mtimeFilmT.getFilmBoxOffice());
            boxRanking.add(filmInfo);
        }
        return boxRanking;
    }

    /**
     * 获得即将上映的电影信息
     *
     * @return
     */
    private FilmVo getSoonFilms() {
        //PageHelper.startPage(1, 8);
        RowBounds rowBounds = new RowBounds(0, 8);
        FilmVo soonFilms = new FilmVo();
        Wrapper<MtimeFilmT> filmTWrapper = new EntityWrapper<>();
        filmTWrapper.eq("film_status", 2);
        List<MtimeFilmT> filmTS = filmTMapper.selectPage(rowBounds, filmTWrapper);//所以热映的电影
        Integer filmNum = filmTMapper.selectCount(filmTWrapper);
        //PageInfo<MtimeFilmT> pageInfo = new PageInfo<>(filmTS);
        //long filmNum = pageInfo.getTotal();//获得热映电影的总条目数
        soonFilms.setFilmNum(filmNum);
        ArrayList<FilmInfo> filmInfos = new ArrayList<>();
        for (MtimeFilmT mtimeFilmT : filmTS) {
            FilmInfo filmInfo = new FilmInfo();
            filmInfo.setFilmId(mtimeFilmT.getUuid().toString());
            filmInfo.setFilmType(mtimeFilmT.getFilmType());
            filmInfo.setImgAddress(mtimeFilmT.getImgAddress());
            filmInfo.setFilmName(mtimeFilmT.getFilmName());
            filmInfo.setExpectNum(mtimeFilmT.getFilmPresalenum());
            filmInfo.setShowTime(StringTool.date2String(mtimeFilmT.getFilmTime()));
            filmInfos.add(filmInfo);
        }
        soonFilms.setFilmInfo(filmInfos);
        return soonFilms;
    }

    /**
     * 获得热映影片
     *
     * @return
     */
    private FilmVo getHotFilms() {
        //PageHelper.startPage(1,8);
        RowBounds rowBounds = new RowBounds(0, 8);
        FilmVo hotFilms = new FilmVo();
        Wrapper<MtimeFilmT> filmTWrapper = new EntityWrapper<>();
        filmTWrapper.eq("film_status", 1);
        List<MtimeFilmT> filmTS = filmTMapper.selectPage(rowBounds, filmTWrapper);//所以热映的电影
        //PageInfo<MtimeFilmT> pageInfo = new PageInfo<>(filmTS);
        //long filmNum = pageInfo.getTotal();//获得热映电影的总条目数
        Integer filmNum = filmTMapper.selectCount(filmTWrapper);
        hotFilms.setFilmNum(filmNum);
        ArrayList<FilmInfo> filmInfos = new ArrayList<>();
        for (MtimeFilmT mtimeFilmT : filmTS) {
            FilmInfo filmInfo = new FilmInfo();
            filmInfo.setFilmId(mtimeFilmT.getUuid().toString());
            filmInfo.setFilmType(mtimeFilmT.getFilmType());
            filmInfo.setImgAddress(mtimeFilmT.getImgAddress());
            filmInfo.setFilmName(mtimeFilmT.getFilmName());
            filmInfo.setFilmScore(mtimeFilmT.getFilmScore());
            filmInfo.setScore(mtimeFilmT.getFilmScore());
            filmInfos.add(filmInfo);
        }
        hotFilms.setFilmInfo(filmInfos);
        return hotFilms;
    }

    /**
     * 获得所有还在使用的banner信息
     *
     * @return
     */
    private ArrayList<BannerVo> getBanners() {
        Wrapper<MtimeBannerT> bannerTWrapper = new EntityWrapper();
        bannerTWrapper.eq("is_valid", 0);//0表示没有弃用的
        List<MtimeBannerT> bannerTS = bannerTMapper.selectList(bannerTWrapper);
        ArrayList<BannerVo> bannerVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(bannerTS)) {
            for (MtimeBannerT bannerT : bannerTS) {
                BannerVo bannerVo = new BannerVo();
                bannerVo.setBannerId(bannerT.getUuid().toString());
                bannerVo.setBannerAddress(bannerT.getBannerAddress());
                bannerVo.setBannerUrl(bannerT.getBannerUrl());
                bannerVos.add(bannerVo);
            }
        }
        return bannerVos;
    }

}
