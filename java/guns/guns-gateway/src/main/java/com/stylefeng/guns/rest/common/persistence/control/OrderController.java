package com.stylefeng.guns.rest.common.persistence.control;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jiaxingrong.utils.StringTool;
import com.stylefeng.guns.cinema.CinemaService;
import com.stylefeng.guns.film.FilmService;
import com.stylefeng.guns.order.OrderService;
import com.stylefeng.guns.order.vo.OrderField;
import com.stylefeng.guns.order.vo.OrderReqVo;
import com.stylefeng.guns.order.vo.OrderRespVo;
import com.stylefeng.guns.rest.model.Result;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-11 10:52
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Reference(interfaceClass = OrderService.class)
    private OrderService orderService;

    @Reference(interfaceClass = CinemaService.class)
    private CinemaService cinemaService;

    @Reference(interfaceClass = UserService.class)
    private UserService userService;

    @Reference(interfaceClass = FilmService.class)
    private FilmService filmService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @RequestMapping("buyTickets")
    public Result buyTickets(OrderReqVo orderReqVo, @RequestHeader String Authorization){
        //String header = request.getHeader("Authorization");

        String username = jwtTokenUtil.getUsernameFromToken(Authorization.substring(7));
        Integer fieldId = orderReqVo.getFieldId();
        OrderField orderField = cinemaService.queryFieldHallNameByFiledId(fieldId);
        String seat_address = cinemaService.queryseat_addressByHallId(orderField.getHall_id());
        Integer userId = userService.queryUserId(username);
        String filmName = filmService.quereFilmNameByFilmId(orderField.getFilm_id());
        String cinemaName = cinemaService.queryCinemaNameByCinemaId(orderField.getCinema_id());
        OrderRespVo orderRespVo = orderService.buyTickets(orderReqVo,seat_address,userId,orderField,filmName,cinemaName);


        if (orderRespVo==null) return Result.failure();
        if (!StringTool.isNotNull(orderRespVo.getOrderId())) return Result.statusIsOne("该订单不存在!");
        return Result.ok(orderRespVo);
    }

    @RequestMapping("getOrderInfo")
    public Result getOrderInfo(Integer nowPage,Integer pageSize,@RequestHeader String Authorization){
        String username = jwtTokenUtil.getUsernameFromToken(Authorization.substring(7));
        Integer userId = userService.queryUserId(username);
        List<OrderRespVo> respVos = orderService.getOrderInfo(userId,nowPage,pageSize);
        if (respVos==null) return Result.failure();
        if (respVos.size()==0) return Result.statusIsOne("订单列表为空哦！~");
        return Result.ok(respVos);
    }



}
