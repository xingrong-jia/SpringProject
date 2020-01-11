package com.stylefeng.guns.rest.model;

import lombok.Data;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-07 21:02
 */
@Data
public class Result<T> {

    private Integer status;

    private String msg;

    private T data;

    private String imgPre = "http://img.meetingshop.cn/";

    private Integer nowPage;

    private Integer totalPage;

    public static Result ok(Object o, Integer nowPage, Integer totalPage) {
        return new Result(0, o, nowPage, totalPage);
    }

    public Result(Integer status, T data, Integer nowPage, Integer totalPage) {
        this.status = status;
        this.data = data;
        this.nowPage = nowPage;
        this.totalPage = totalPage;
    }

    public Result(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

    public static Result ok(String msg) {
        return new Result(0, msg);
    }

    public static Result ok(Object o) {
        return new Result(0, o);
    }
    public static Result ok(Object o,String imgPre) {
        return new Result(0, o,imgPre);
    }

    public static Result ok(String imgPre, Object o) {
        return new Result(0, o, imgPre);
    }


    public static Result failure() {
        return new Result(999, "系统出现异常，请联系管理员!");
    }

    public static Result statusIsOne(String msg) {
        return new Result(1, msg);
    }

    public Result(Integer status, T data, String imgPre) {
        this.status = status;
        this.data = data;
        this.imgPre = imgPre;
    }

    public Result(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Result() {
    }
}
