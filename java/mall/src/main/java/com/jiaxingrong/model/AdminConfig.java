package com.jiaxingrong.model;

import lombok.Data;

@Data
public class AdminConfig {


    /**
     * {
     * 	"litemall_mall_phone": "3333aaa11",
     * 	"litemall_mall_address": "ggggaa11",
     * 	"litemall_mall_name": "hhh1aaa11",
     * 	"litemall_mall_qq": "3242342342342342aaa11"
     * }
     */
    /**
     * litemall_mall_phone : 3333aaa11
     * litemall_mall_address : ggggaa11
     * litemall_mall_name : hhh1aaa11
     * litemall_mall_qq : 3242342342342342aaa11
     */
    private String litemall_mall_phone;
    private String litemall_mall_address;
    private String litemall_mall_name;
    private String litemall_mall_qq;

    /**
     * litemall_express_freight_min : 81
     * litemall_express_freight_value : 881
     */
    private String litemall_express_freight_min;
    private String litemall_express_freight_value;


    /**
     * litemall_wx_index_new : 6
     * litemall_wx_catlog_goods : 4
     * litemall_wx_catlog_list : 4
     * litemall_wx_share : true
     * litemall_wx_index_brand : 4
     * litemall_wx_index_hot : 6
     * litemall_wx_index_topic : 4
     */
    private String litemall_wx_index_new;
    private String litemall_wx_catlog_goods;
    private String litemall_wx_catlog_list;
    private String litemall_wx_share;
    private String litemall_wx_index_brand;
    private String litemall_wx_index_hot;
    private String litemall_wx_index_topic;

    /**
     * litemall_order_comment : 7
     * litemall_order_unpaid : 30
     * litemall_order_unconfirm : 7
     */
    private String litemall_order_comment;
    private String litemall_order_unpaid;
    private String litemall_order_unconfirm;


}
