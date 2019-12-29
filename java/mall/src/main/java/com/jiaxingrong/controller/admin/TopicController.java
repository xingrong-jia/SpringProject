package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.model.Topic;
import com.jiaxingrong.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/topic")
public class TopicController {

    @Autowired
    TopicService topicService;

    @RequestMapping("list")
    public BaseReqVo list(Laypage laypage){
        Map map = topicService.list(laypage);
        return BaseReqVo.ok(map);
    }

    @RequestMapping("create")
    public BaseReqVo create(@RequestBody Topic topic){
        topic = topicService.addTopic(topic);
        return BaseReqVo.ok(topic);
    }

    @RequestMapping("update")
    public BaseReqVo update(@RequestBody Topic topic){
        topic = topicService.updateTopic(topic);
        return BaseReqVo.ok(topic);
    }

    @RequestMapping("delete")
    public BaseReqVo delete(@RequestBody Topic topic){
        int i = topicService.deleteTopic(topic);
        return BaseReqVo.ok();
    }
}
