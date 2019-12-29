package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.Comment;
import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping("list")
    public BaseReqVo list(Laypage laypage){
        Map map = commentService.list(laypage);
        return BaseReqVo.ok(map);
    }



    @RequestMapping("delete")
    public BaseReqVo delete(@RequestBody  Comment comment){
        boolean b = commentService.deleteComment(comment);
        if (b) return BaseReqVo.ok();
        return new BaseReqVo("失败",500);
    }
}
