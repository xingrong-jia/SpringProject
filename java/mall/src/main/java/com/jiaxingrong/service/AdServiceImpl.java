package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.AdMapper;
import com.jiaxingrong.model.*;
import com.jiaxingrong.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    AdMapper adMapper;

    @Override
    public Map<String, Object> list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        AdExample adExample = new AdExample();
        adExample.setOrderByClause(laypage.getSort()+" "+laypage.getOrder());
        AdExample.Criteria criteria = adExample.createCriteria();
        if (StringTool.isNotNull(laypage.getName())) {
            criteria.andNameLike("%"+laypage.getName()+"%");
        }
        if (StringTool.isNotNull(laypage.getContent())) {
            criteria.andContentLike("%"+laypage.getContent()+"%");
        }
        criteria.andDeletedEqualTo(false);
        List<Ad> ads =  adMapper.selectByExample(adExample);
        PageInfo<Ad> adPageInfo = new PageInfo<>(ads);
        long total = adPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", ads);
        return map;
    }

    @Override
    public Ad addAd(Ad ad) {
        ad.setAddTime(new Date());
        ad.setUpdateTime(new Date());
        ad.setDeleted(false);
        adMapper.insertSelective(ad);
        return ad;
    }

    @Override
    public Ad updateAd(Ad ad) {
        ad.setUpdateTime(new Date());
        int update = adMapper.updateByPrimaryKeySelective(ad);
        return ad;
    }

    @Override
    public Ad deleteAd(Ad ad) {
        ad.setUpdateTime(new Date());
        ad.setDeleted(true);
        int update = adMapper.updateByPrimaryKeySelective(ad);
        return ad;
    }
}
