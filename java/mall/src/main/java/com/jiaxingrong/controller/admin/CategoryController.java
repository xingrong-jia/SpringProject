package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.CateGory;
import com.jiaxingrong.service.CateGoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/category")
public class CategoryController {

    @Autowired
    CateGoryService cateGoryService;

    @RequestMapping("list")
    public BaseReqVo<List> category(){
        List<CateGory> regions = cateGoryService.list();
        return new BaseReqVo<>(regions,"成功",0);
    }

    @RequestMapping("l1")
    public BaseReqVo categoryL1(){
        List<Map> regions = cateGoryService.listL1();
        return new BaseReqVo<>(regions,"成功",0);
    }

    @RequestMapping("create")
    public BaseReqVo createCategory(@RequestBody CateGory cateGory){
        cateGory = cateGoryService.addCateGory(cateGory);
        return new BaseReqVo<>(cateGory,"成功",0);
    }

    @RequestMapping("update")
    public BaseReqVo updateCategory(@RequestBody CateGory cateGory){
        int i = cateGoryService.updateCateGory(cateGory);
        if (i!=1){
            return new BaseReqVo<>("失败",402);
        }
        return new BaseReqVo<>("成功",0);
    }

    @RequestMapping("delete")
    public BaseReqVo deleteCategory(@RequestBody CateGory cateGory){
        int i = cateGoryService.deleteCateGory(cateGory);
        if (i!=1){
            return new BaseReqVo<>("失败",402);
        }
        return new BaseReqVo<>("成功",0);
    }


}
