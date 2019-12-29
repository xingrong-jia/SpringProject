package com.jiaxingrong.service;

import com.jiaxingrong.model.AddGoods;
import com.jiaxingrong.model.Goods;
import com.jiaxingrong.model.Laypage;

import java.util.Map;

public interface GoodsService {
    Map<String, Object> list(Laypage laypage);

    Map<String, Object> catAndBrand();

    boolean addGoods(AddGoods addGoods);

    Map queryGoodsById(Integer id);

    boolean updateGoods(AddGoods addGoods);

    boolean deleteGoods(Goods goods);

    Map count();

    Map wxList(Laypage laypage);

    Map detail(Integer id);

    Map related(Integer id);
}
