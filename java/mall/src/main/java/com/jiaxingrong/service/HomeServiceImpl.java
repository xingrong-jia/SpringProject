package com.jiaxingrong.service;

import com.jiaxingrong.mapper.*;
import com.jiaxingrong.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    CouponMapper couponMapper;

    @Autowired
    CateGoryMapper cateGoryMapper;

    @Autowired
    AdMapper adMapper;

    @Autowired
    BrandMapper brandMapper;

    @Override
    public Map index() {
        HashMap<String, List> map = new HashMap<>();
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andDeletedEqualTo(false);
        criteria.andIsHotEqualTo(true);
        criteria.andIsNewEqualTo(true);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        map.put("newGoodsList",goods);

        CouponExample couponExample = new CouponExample();
        couponExample.createCriteria().andDeletedEqualTo(false);
        List<Coupon> coupons = couponMapper.selectByExample(couponExample);
        map.put("couponList",coupons);

        CateGoryExample cateGoryExample = new CateGoryExample();
        CateGoryExample.Criteria criteria1 = cateGoryExample.createCriteria();
        criteria1.andDeletedEqualTo(false);
        criteria1.andPidEqualTo(0);
        List<CateGory> cateGories = cateGoryMapper.selectByExample(cateGoryExample);
        map.put("channel",cateGories);

        AdExample adExample = new AdExample();
        AdExample.Criteria criteria2 = adExample.createCriteria();
        criteria2.andDeletedEqualTo(false);
        criteria2.andEnabledEqualTo(true);
        List<Ad> ads = adMapper.selectByExample(adExample);
        map.put("banner",ads);

        BrandExample brandExample = new BrandExample();
        brandExample.createCriteria().andDeletedEqualTo(false);
        List<Brand> brands = brandMapper.selectByExample(brandExample);
        map.put("brandList",brands.subList(0,3));

        criteria.andIsNewEqualTo(false);
        List<Goods> goods1 = goodsMapper.selectByExample(goodsExample);
        map.put("hotGoodsList",goods1);

        return map;
    }
}
