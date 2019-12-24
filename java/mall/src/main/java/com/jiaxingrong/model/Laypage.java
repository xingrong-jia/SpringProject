package com.jiaxingrong.model;

import lombok.Data;

@Data
public class Laypage {
    Integer page;
    Integer limit;
    String sort;
    String order;
    String username;
    String mobile;
    String name;
    Integer userId;
    //查询收藏时的商品id
    Integer valueId;
    //查询足迹时的商品id
    Integer goodsId;

    //搜索历史的关键字
    String keyword;

    //意见反馈的id
    Integer id;
}
