package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.*;
import com.jiaxingrong.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class MallController {

    @Autowired
    RegionService regionService;

    @Autowired
    BrandService brandService;

    @Autowired
    PicService picService;

    @Autowired
    CateGoryService cateGoryService;

    @Autowired
    OrderService orderService;

    @Autowired
    IssueService issueService;

    @Autowired
    KeywordService keywordService;

    @RequestMapping("region/list")
    public BaseReqVo<List> region(){
        List<Region> regions = regionService.list();
        return new BaseReqVo<>(regions,"成功",0);
    }

    @RequestMapping("brand/list")
    public BaseReqVo<Map> brandList(Laypage laypage){
        Map<String,Object> map = brandService.list(laypage);
        return new BaseReqVo<>(map,"成功",0);
    }

    @RequestMapping("storage/create")
    public BaseReqVo storage(MultipartFile file){
        Pic pic = picService.storage(file);
        if (pic!=null){
            return new BaseReqVo(pic,"成功",0);
        }
        return new BaseReqVo(pic,"失败",402);
    }

    @RequestMapping("brand/create")
    public BaseReqVo addBrand(@RequestBody Brand brand){
        brand = brandService.addBrand(brand);
        return new BaseReqVo(brand,"成功",0);
    }

    @RequestMapping("brand/update")
    public BaseReqVo updateBrand(@RequestBody Brand brand){
        int i = brandService.updateBrand(brand);
        return new BaseReqVo(brand,"成功",0);
    }

    @RequestMapping("brand/delete")
    public BaseReqVo deleteBrand(@RequestBody Brand brand){
        int i = brandService.deleteBrand(brand);
        return new BaseReqVo(null,"成功",0);
    }

    @RequestMapping("category/list")
    public BaseReqVo<List> category(){
        List<CateGory> regions = cateGoryService.list();
        return new BaseReqVo<>(regions,"成功",0);
    }

    @RequestMapping("category/l1")
    public BaseReqVo categoryL1(){
        List<Map> regions = cateGoryService.listL1();
        return new BaseReqVo<>(regions,"成功",0);
    }

    @RequestMapping("category/create")
    public BaseReqVo createCategory(@RequestBody CateGory cateGory){
        cateGory = cateGoryService.addCateGory(cateGory);
        return new BaseReqVo<>(cateGory,"成功",0);
    }

    @RequestMapping("category/update")
    public BaseReqVo updateCategory(@RequestBody CateGory cateGory){
        int i = cateGoryService.updateCateGory(cateGory);
        if (i!=1){
            return new BaseReqVo<>("失败",402);
        }
        return new BaseReqVo<>("成功",0);
    }

    @RequestMapping("category/delete")
    public BaseReqVo deleteCategory(@RequestBody CateGory cateGory){
        int i = cateGoryService.deleteCateGory(cateGory);
        if (i!=1){
            return new BaseReqVo<>("失败",402);
        }
        return new BaseReqVo<>("成功",0);
    }

    @RequestMapping("order/list")
    public BaseReqVo<Map> orderList(Laypage laypage){
        Map<String,Object> map = orderService.list(laypage);
        return new BaseReqVo<>(map,"成功",0);
    }

    @RequestMapping("issue/list")
    public BaseReqVo issue(Laypage lazypage){
        Map<String,Object> issues = issueService.list(lazypage);
        return new BaseReqVo<>(issues,"成功",0);
    }


    @RequestMapping("issue/create")
    public BaseReqVo createIssue(@RequestBody Issue issue){
        issue = issueService.addIssue(issue);
        return new BaseReqVo<>(issue,"成功",0);
    }

    @RequestMapping("issue/update")
    public BaseReqVo updateIssue(@RequestBody Issue issue){
        issue = issueService.updateIssue(issue);
        return new BaseReqVo<>(issue,"成功",0);
    }

    @RequestMapping("issue/delete")
    public BaseReqVo deleteIssue(@RequestBody Issue issue){
        issue = issueService.deleteIssue(issue);
        return new BaseReqVo<>("成功",0);
    }

    @RequestMapping("keyword/list")
    public BaseReqVo keyword(Laypage laypage){
        Map<String,Object> map = keywordService.list(laypage);
        return new BaseReqVo<>(map,"成功",0);
    }


    @RequestMapping("keyword/create")
    public BaseReqVo createKeyword(@RequestBody Keyword keyword){
        keyword = keywordService.addKeyword(keyword);
        return new BaseReqVo<>(keyword,"成功",0);
    }

    @RequestMapping("keyword/update")
    public BaseReqVo updateKeyword(@RequestBody Keyword keyword){
        keyword = keywordService.updateKeyword(keyword);
        return new BaseReqVo<>(keyword,"成功",0);
    }

    @RequestMapping("keyword/delete")
    public BaseReqVo deleteKeyword(@RequestBody Keyword keyword){
        keyword = keywordService.deleteKeyword(keyword);
        return new BaseReqVo<>("成功",0);
    }
}
