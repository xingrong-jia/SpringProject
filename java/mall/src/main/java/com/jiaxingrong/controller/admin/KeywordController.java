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
@RequestMapping("admin/keyword")
public class KeywordController {


    @Autowired
    KeywordService keywordService;



    @RequestMapping("list")
    public BaseReqVo keyword(Laypage laypage){
        Map<String,Object> map = keywordService.list(laypage);
        return new BaseReqVo<>(map,"成功",0);
    }


    @RequestMapping("create")
    public BaseReqVo createKeyword(@RequestBody Keyword keyword){
        keyword = keywordService.addKeyword(keyword);
        return new BaseReqVo<>(keyword,"成功",0);
    }

    @RequestMapping("update")
    public BaseReqVo updateKeyword(@RequestBody Keyword keyword){
        keyword = keywordService.updateKeyword(keyword);
        return new BaseReqVo<>(keyword,"成功",0);
    }

    @RequestMapping("delete")
    public BaseReqVo deleteKeyword(@RequestBody Keyword keyword){
        keyword = keywordService.deleteKeyword(keyword);
        return new BaseReqVo<>("成功",0);
    }
}
