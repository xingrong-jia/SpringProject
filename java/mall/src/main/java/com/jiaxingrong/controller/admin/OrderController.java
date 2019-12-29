package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.Comment;
import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.service.CommentService;
import com.jiaxingrong.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    CommentService commentService;

    @RequestMapping("reply")
    public BaseReqVo reply(@RequestBody Comment comment){
        boolean b = commentService.reply(comment);
        if (b) return BaseReqVo.ok();
        return new BaseReqVo("订单商品已回复!",622);
    }

    @RequestMapping("list")
    public BaseReqVo<Map> orderList(Laypage laypage){
        Map<String,Object> map = orderService.list(laypage);
        return  BaseReqVo.ok(map);
    }

    @RequestMapping("detail")
    public BaseReqVo detail(Integer id){
        Map map = orderService.detail(id);
        return  BaseReqVo.ok(map);
    }
}
