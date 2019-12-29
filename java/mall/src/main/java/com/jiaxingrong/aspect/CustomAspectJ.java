package com.jiaxingrong.aspect;

import com.jiaxingrong.mapper.LogMapper;
import com.jiaxingrong.model.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Aspect
@Component
public class CustomAspectJ {

    @Autowired
    LogMapper logMapper;



    @Pointcut("@annotation(com.jiaxingrong.anntation.LogRecord)")
    public void myPointCut() {
    }


    @Around(value = "myPointCut()")
    public Object myAround(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            Log log = new Log();
            int errno = ((BaseReqVo) proceed).getErrno();
            if (errno == 0) {
                Subject subject = SecurityUtils.getSubject();
                Admin admin = (Admin) subject.getPrincipal();
                log.setAdmin(admin.getUsername());
                log.setStatus(true);
            } else {
                log.setStatus(false);
            }

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();


            log.setIp(request.getRemoteAddr());
            String URL = request.getRequestURI();
            if (URL.contains("login")) {
                if (errno != 0) {
                    log.setAdmin("匿名用户");
                }
                log.setAction("登录");
            }
            if (URL.contains("logout")) {
                log.setAction("退出");
            }
            log.setResult(((BaseReqVo) proceed).getErrmsg());
            log.setType(1);
            log.setAddTime(new Date());
            log.setUpdateTime(new Date());
            log.setDeleted(false);
            logMapper.insertSelective(log);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }


    @Pointcut("@annotation(com.jiaxingrong.anntation.AdminRecord)")
    public void myPointCut2() {
    }

    @Around(value = "myPointCut2()")
    public Object myAround2(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Admin adminArgs = (Admin) args[0];
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            Log log = new Log();
            int errno = ((BaseReqVo) proceed).getErrno();
            Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
            log.setAdmin(admin.getUsername());
            if (errno == 0) {
                log.setStatus(true);
                log.setResult(adminArgs.getUsername());
            } else {
                log.setStatus(false);
                log.setResult(((BaseReqVo) proceed).getErrmsg());
            }

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            log.setIp(request.getRemoteAddr());
            String URL = request.getRequestURI();
            if (URL.contains("create")) {
                log.setAction("新增管理员");
                log.setType(1);
            }
            if (URL.contains("update")) {
                log.setAction("修改管理员");
                log.setType(1);
            }

            if (URL.contains("delete")) {
                log.setAction("删除管理员");
                log.setType(3);
            }

            log.setAddTime(new Date());
            log.setUpdateTime(new Date());
            log.setDeleted(false);
            logMapper.insertSelective(log);
        } catch (Throwable throwable) {

        }
        return proceed;
    }


    @Pointcut("@annotation(com.jiaxingrong.anntation.ConfigRecord)")
    public void myPointCut3() {
    }

    @Around(value = "myPointCut3()")
    public Object myAround3(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            Log log = new Log();
            int errno = ((BaseReqVo) proceed).getErrno();
            Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
            log.setAdmin(admin.getUsername());
            if (errno == 0) {
                log.setStatus(true);
            } else {
                log.setStatus(false);

            }

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            log.setResult(((BaseReqVo) proceed).getErrmsg());
            log.setIp(request.getRemoteAddr());
            String URL = request.getRequestURI();
            if (URL.contains("mall")) {
                log.setAction("修改商场信息");
                log.setType(3);
            }
            if (URL.contains("express")) {
                log.setAction("修改运费配置");
                log.setType(3);
            }

            if (URL.contains("order")) {
                log.setAction("修改订单配置");
                log.setType(3);
            }
            if (URL.contains("wx")) {
                log.setAction("修改小程序配置");
                log.setType(3);
            }
            log.setAddTime(new Date());
            log.setUpdateTime(new Date());
            log.setDeleted(false);
            logMapper.insertSelective(log);
        } catch (Throwable throwable) {

        }
        return proceed;
    }


    @Pointcut("@annotation(com.jiaxingrong.anntation.GoodsRecord)")
    public void myPointCut4() {
    }

    @Around(value = "myPointCut4()")
    public Object myAround4(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            Log log = new Log();
            int errno = ((BaseReqVo) proceed).getErrno();
            Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
            log.setAdmin(admin.getUsername());
            if (errno == 0) {
                log.setStatus(true);
            } else {
                log.setStatus(false);
                log.setResult(((BaseReqVo) proceed).getErrmsg());
            }

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            log.setIp(request.getRemoteAddr());
            String URL = request.getRequestURI();
            if (URL.contains("create")) {
                AddGoods addGoods = (AddGoods) args[0];
                log.setResult("新增商品id为："+addGoods.getGoods().getId());
                log.setAction("新增商品信息");
                log.setType(5);
            }
            if (URL.contains("update")) {
                AddGoods addGoods = (AddGoods) args[0];
                log.setResult("修改商品id为："+addGoods.getGoods().getId());
                log.setAction("修改商品信息");
                log.setType(5);
            }

            if (URL.contains("delete")) {
                Goods goods = (Goods) args[0];
                log.setResult("删除商品id为："+goods.getId());
                log.setAction("删除商品");
                log.setType(2);
            }

            log.setAddTime(new Date());
            log.setUpdateTime(new Date());
            log.setDeleted(false);
            logMapper.insertSelective(log);
        } catch (Throwable throwable) {

        }
        return proceed;
    }

/*    @Pointcut("@annotation(com.jiaxingrong.anntation.ForwardRecord)")
    public void myPointCut4(){}

    @After(value = "myPointCut4()")
    public Object myAround4(ProceedingJoinPoint joinPoint){
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String requestURI = request.getRequestURL().toString();
            if (requestURI.contains(":8080/admin/")){
                proceed="forward:http://localhost:9527/#/login?redirect=%2Fdashboard";
            }else {
                proceed="forward:http://localhost:9527/#/login?redirect=%2Fdashboard";
            }
        } catch (Throwable throwable) {

        }
        return proceed;
    }*/

}
