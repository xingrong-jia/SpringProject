package com.stylefeng.guns.rest.common.persistence.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jiaxingrong.utils.CollectionUtils;
import com.stylefeng.guns.cinema.CinemaService;
import com.stylefeng.guns.cinema.vo.*;
import com.stylefeng.guns.order.vo.OrderField;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-10 15:17
 */
@Component
@Service(interfaceClass = CinemaService.class)
public class CinemaSeriveImpl implements CinemaService {

    @Autowired
    MtimeCinemaTMapper cinemaTMapper;

    @Autowired
    MtimeAreaDictTMapper areaDictTMapper;

    @Autowired
    MtimeBrandDictTMapper brandDictTMapper;

    @Autowired
    MtimeHallDictTMapper hallDictTMapper;

    @Autowired
    MtimeHallFilmInfoTMapper hallFilmInfoTMapper;

    @Autowired
    MtimeFieldTMapper fieldTMapper;


    @Override
    public Map<String, Object> getCinemas(CinemasReqVo cinemasReqVo) {
        Map<String, Object> map = new HashMap<>();
        Wrapper<MtimeCinemaT> wrapper = new EntityWrapper<>();
        HashMap<String, Object> eqMap = new HashMap<>();
        if (cinemasReqVo.getBrandId() != 99) {
            eqMap.put("brand_id", cinemasReqVo.getBrandId());
        }
        if (cinemasReqVo.getHallType() != 99) {
            wrapper.like("hall_ids", "#" + cinemasReqVo.getHallType() + "#");
        }
        if (cinemasReqVo.getDistrictId() != 99) {
            eqMap.put("area_id", cinemasReqVo.getDistrictId());
        }
        wrapper.allEq(eqMap);
        RowBounds rowBounds = new RowBounds(cinemasReqVo.getNowPage() - 1, cinemasReqVo.getPageSize());
        Integer count = cinemaTMapper.selectCount(wrapper);
        List<MtimeCinemaT> cinemaTS = cinemaTMapper.selectPage(rowBounds, wrapper);
        ArrayList<CinemaInfo> cinemaInfos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(cinemaTS)) {
            for (MtimeCinemaT cinemaT : cinemaTS) {
                CinemaInfo cinemaInfo = new CinemaInfo();
                cinemaInfo.setUuid(cinemaT.getUuid());
                cinemaInfo.setCinemaName(cinemaT.getCinemaName());
                cinemaInfo.setAddress(cinemaT.getCinemaAddress());
                cinemaInfo.setMinimumPrice(Double.valueOf(cinemaT.getMinimumPrice()));
                cinemaInfos.add(cinemaInfo);
            }
        }
        Integer totalPage = count / cinemasReqVo.getPageSize();
        map.put("nowPage", cinemasReqVo.getNowPage());
        map.put("totalPage", totalPage + 1);
        map.put("data", cinemaInfos);
        return map;
    }

    @Override
    public CinemaConditionRespVo getCondition(CinemasReqVo cinemasReqVo) {
        CinemaConditionRespVo conditionRespVo = new CinemaConditionRespVo();
        List<MtimeAreaDictT> areaDictTS = areaDictTMapper.selectList(new EntityWrapper<>());
        List<MtimeBrandDictT> brandDictTS = brandDictTMapper.selectList(new EntityWrapper<>());
        List<MtimeHallDictT> hallDictTS = hallDictTMapper.selectList(new EntityWrapper<>());
        ArrayList<AreaVo> areaVos = new ArrayList<>();
        ArrayList<BrandVo> brandVos = new ArrayList<>();
        ArrayList<HallTypeVo> hallTypeVos = new ArrayList<>();
        for (MtimeAreaDictT areaDictT : areaDictTS) {
            AreaVo areaVo = new AreaVo();
            if (areaDictT.getUuid().equals(cinemasReqVo.getAreaId())) {
                areaVo.setActive(true);
            } else {
                areaVo.setActive(false);
            }
            areaVo.setAreaId(areaDictT.getUuid());
            areaVo.setAreaName(areaDictT.getShowName());
            areaVos.add(areaVo);
        }
        conditionRespVo.setAreaList(areaVos);
        for (MtimeBrandDictT brandDictT : brandDictTS) {
            BrandVo brandVo = new BrandVo();
            if (brandDictT.getUuid().equals(cinemasReqVo.getBrandId())) {
                brandVo.setActive(true);
            } else {
                brandVo.setActive(false);
            }
            brandVo.setBrandId(brandDictT.getUuid());
            brandVo.setBrandName(brandDictT.getShowName());
            brandVos.add(brandVo);
        }
        conditionRespVo.setBrandList(brandVos);
        for (MtimeHallDictT hallDictT : hallDictTS) {
            HallTypeVo hallTypeVo = new HallTypeVo();
            if (hallDictT.getUuid().equals(cinemasReqVo.getHallType())) {
                hallTypeVo.setActive(true);
            } else {
                hallTypeVo.setActive(false);
            }
            hallTypeVo.setHalltypeId(hallDictT.getUuid());
            hallTypeVo.setHalltypeName(hallDictT.getShowName());
            hallTypeVos.add(hallTypeVo);
        }
        conditionRespVo.setHalltypeList(hallTypeVos);
        return conditionRespVo;
    }

    @Override
    public Map<String, Object> getFields(Integer cinemaId) {
        HashMap<String, Object> respMap = new HashMap<>();
        MtimeCinemaT cinemaT = cinemaTMapper.selectById(cinemaId);
        CinemaVo cinemaVo = new CinemaVo();
        if (cinemaT != null) {
            cinemaVo.setCinemaId(cinemaT.getUuid());
            cinemaVo.setCinemaName(cinemaT.getCinemaName());
            cinemaVo.setCinemaAddress(cinemaT.getCinemaAddress());
            cinemaVo.setCinemaPhone(cinemaT.getCinemaPhone());
            cinemaVo.setImgUrl(cinemaT.getImgAddress());
        }
        respMap.put("cinemaInfo", cinemaVo);
        Wrapper<MtimeFieldT> fieldTWrapper = new EntityWrapper<>();
        fieldTWrapper.eq("cinema_id", cinemaId);
        List<MtimeFieldT> mtimeFieldTS = fieldTMapper.selectList(fieldTWrapper);
        List<FilmListVo> filmListVos = new ArrayList<>();

        if (!CollectionUtils.isEmpty(mtimeFieldTS)) {
            Set<Integer> filmId = new HashSet<>();
            HashMap<Integer, List<FilmFields>> listHashMap = new HashMap<>();
            HashMap<Integer, MtimeHallFilmInfoT> tHashMap = new HashMap<>();
            for (MtimeFieldT mtimeFieldT : mtimeFieldTS) {
                Integer filmId1 = mtimeFieldT.getFilmId();
                filmId.add(filmId1);
                FilmFields fields = new FilmFields();
                fields.setBeginTime(mtimeFieldT.getBeginTime());
                fields.setEndTime(mtimeFieldT.getEndTime());
                fields.setFieldId(mtimeFieldT.getUuid());
                fields.setHallName(mtimeFieldT.getHallName());
                fields.setPrice(mtimeFieldT.getPrice().toString());
                MtimeHallFilmInfoT hallFilmInfoT = null;
                boolean key = tHashMap.containsKey(filmId1);
                if (key) {
                    hallFilmInfoT = tHashMap.get(filmId1);
                } else {
                    hallFilmInfoT = hallFilmInfoTMapper.selectHallFilmTnfoByFilmId(filmId1);
                    tHashMap.put(filmId1, hallFilmInfoT);
                }
                fields.setLanguage(hallFilmInfoT.getFilmLanguage());
                boolean containsKey = listHashMap.containsKey(filmId1);
                if (containsKey) {
                    List<FilmFields> list = listHashMap.get(filmId1);
                    list.add(fields);
                } else {
                    List<FilmFields> filmFields = new ArrayList<>();
                    filmFields.add(fields);
                    listHashMap.put(filmId1, filmFields);
                }
            }
            for (Integer integer : filmId) {
                FilmListVo filmListVo = new FilmListVo();
                List<FilmFields> filmFields = listHashMap.get(integer);
                MtimeHallFilmInfoT hallFilmInfoT = tHashMap.get(integer);
                filmListVo.setActors(hallFilmInfoT.getActors());
                filmListVo.setFilmCats(hallFilmInfoT.getFilmCats());
                filmListVo.setFilmFields(filmFields);
                filmListVo.setFilmId(integer);
                filmListVo.setFilmLength(hallFilmInfoT.getFilmLength());
                filmListVo.setFilmName(hallFilmInfoT.getFilmName());
                filmListVo.setFilmType(hallFilmInfoT.getFilmLanguage());
                filmListVo.setImgAddress(hallFilmInfoT.getImgAddress());
                filmListVos.add(filmListVo);
            }
        }
        respMap.put("filmList",filmListVos);
        return respMap;
    }

    @Override
    public FieldInfo getFieldInfo(Integer cinemaId, Integer fieldId) {
        FieldInfo fieldInfo = new FieldInfo();
        MtimeCinemaT cinemaT = cinemaTMapper.selectById(cinemaId);
        CinemaVo cinemaInfo = new CinemaVo();
        if (cinemaT!=null){
            cinemaInfo.setCinemaId(cinemaT.getUuid());
            cinemaInfo.setImgUrl(cinemaT.getImgAddress());
            cinemaInfo.setCinemaName(cinemaT.getCinemaName());
            cinemaInfo.setCinemaAddress(cinemaT.getCinemaAddress());
            cinemaInfo.setCinemaPhone(cinemaT.getCinemaPhone());
        }
        fieldInfo.setCinemaInfo(cinemaInfo);
        HallInfo hallInfo = new HallInfo();
        MtimeFieldT fieldT = fieldTMapper.selectById(fieldId);
        if (fieldT!=null){
            hallInfo.setHallFieldId(fieldT.getUuid().toString());
            hallInfo.setHallName(fieldT.getHallName());
            hallInfo.setPrice(fieldT.getPrice());
            MtimeHallDictT hallDictT = hallDictTMapper.selectMtimeHallDictTByHallName(fieldT.getHallName());
            if (hallDictT!=null){
                hallInfo.setSeatFile(hallDictT.getSeatAddress());
            }
            hallInfo.setSoldSeats("1,2,3,5,12");
            fieldInfo.setHallInfo(hallInfo);
        }
        MtimeHallFilmInfoT filmInfoT = hallFilmInfoTMapper.selectHallFilmTnfoByFilmId(fieldT.getFilmId());
        FilmListVo filmInfo = new FilmListVo();
        if (filmInfoT!=null){
            filmInfo.setFilmId(filmInfoT.getFilmId());
            filmInfo.setFilmName(filmInfoT.getFilmName());
            filmInfo.setFilmType(filmInfoT.getFilmLanguage());
            filmInfo.setImgAddress(filmInfoT.getImgAddress());
            filmInfo.setFilmCats(filmInfoT.getFilmCats());
            filmInfo.setFilmLength(filmInfoT.getFilmLength());
        }
        fieldInfo.setFilmInfo(filmInfo);

        return fieldInfo;
    }

    @Override
    public OrderField queryFieldHallNameByFiledId(Integer filedId) {
        MtimeFieldT fieldT = fieldTMapper.selectById(filedId);
        OrderField orderField = new OrderField();
        if (fieldT!=null) {
            orderField.setUUID(fieldT.getUuid());
            orderField.setCinema_id(fieldT.getCinemaId());
            orderField.setFilm_id(fieldT.getFilmId());
            orderField.setBegin_time(fieldT.getBeginTime());
            orderField.setEnd_time(fieldT.getEndTime());
            orderField.setHall_id(fieldT.getHallId());
            orderField.setHall_name(fieldT.getHallName());
            orderField.setPrice(fieldT.getPrice());
        }
        return orderField;
    }

    @Override
    public String queryseat_addressByShow_name(String hallName) {
        String seat_address = hallDictTMapper.selectSeat_addressByShow_name(hallName);
        return seat_address;
    }

    @Override
    public String queryCinemaNameByCinemaId(Integer cinema_id) {
        MtimeCinemaT mtimeCinemaT = cinemaTMapper.selectById(cinema_id);
        if (mtimeCinemaT!=null) return mtimeCinemaT.getCinemaName();
        return null;
    }

    @Override
    public String queryseat_addressByHallId(Integer hall_id) {
        MtimeHallDictT hallDictT = hallDictTMapper.selectById(hall_id);
        if (hallDictT!=null) return hallDictT.getSeatAddress();
        return null;
    }

    @Override
    public String queryFilmNameByFilmName(Integer filmId) {
        return hallFilmInfoTMapper.selectFilmNameByFilmName(filmId);
    }
}
