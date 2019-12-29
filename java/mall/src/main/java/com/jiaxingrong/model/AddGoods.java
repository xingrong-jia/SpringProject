package com.jiaxingrong.model;

import lombok.Data;

import java.util.List;

@Data
public class AddGoods {

    Goods goods;
    List<Goods_specification> specifications;
    List<Goods_product> products;
    List<Goods_attribute> attributes;

}
