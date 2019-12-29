package com.jiaxingrong.controller.admin;


import com.jiaxingrong.anntation.GoodsRecord;
import com.jiaxingrong.model.AddGoods;
import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.Goods;
import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;



    @RequestMapping("list")
    public BaseReqVo list(Laypage laypage){
        Map<String,Object> map = goodsService.list(laypage);
        return BaseReqVo.ok(map);
    }

    @RequestMapping("catAndBrand")
    public BaseReqVo catAndBrand(){
        Map<String,Object> map = goodsService.catAndBrand();
        return BaseReqVo.ok(map);
    }

    @GoodsRecord
    @RequestMapping("create")
    public BaseReqVo create(@RequestBody AddGoods addGoods){
        boolean b = goodsService.addGoods(addGoods);
        if (b) return BaseReqVo.ok();
        return new BaseReqVo("失败",400);
    }

    @RequestMapping("detail")
    public BaseReqVo detail(Integer id){
        Map map = goodsService.queryGoodsById(id);
        return new BaseReqVo(map,"成功",0);
    }

    @GoodsRecord
    @RequestMapping("update")
    public BaseReqVo update(@RequestBody AddGoods addGoods){
        boolean b = goodsService.updateGoods(addGoods);
        if (b) return BaseReqVo.ok();
        return new BaseReqVo("失败",400);
    }

    @GoodsRecord
    @RequestMapping("delete")
    public BaseReqVo delete(@RequestBody Goods goods){
        boolean b = goodsService.deleteGoods(goods);
        return BaseReqVo.ok();
    }
}
