package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.model.Storage;
import com.jiaxingrong.model.Topic;
import com.jiaxingrong.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("admin/storage")
public class StorageController {

    @Autowired
    StorageService storageService;

    @RequestMapping("create")
    public BaseReqVo storage(MultipartFile file){
        Storage storage = storageService.storage(file);
        if (storage!=null){
            return new BaseReqVo(storage,"成功",0);
        }
        return new BaseReqVo(storage,"失败",402);
    }

    @RequestMapping("list")
    public BaseReqVo list(Laypage laypage){
        Map map = storageService.list(laypage);
        return BaseReqVo.ok(map);
    }



    @RequestMapping("update")
    public BaseReqVo update(@RequestBody Storage storage){
        storage = storageService.updateTopic(storage);
        return BaseReqVo.ok(storage);
    }

    @RequestMapping("delete")
    public BaseReqVo delete(@RequestBody Storage storage){
        int i = storageService.deleteTopic(storage);
        return BaseReqVo.ok();
    }
}
