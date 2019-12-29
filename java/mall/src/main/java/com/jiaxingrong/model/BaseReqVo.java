package com.jiaxingrong.model;

import lombok.Data;

@Data
public class BaseReqVo<T> {

    T data;

    String errmsg;

    int errno;

    public BaseReqVo(T data, String errmsg, int errno) {
        this.data = data;
        this.errmsg = errmsg;
        this.errno = errno;
    }

    public BaseReqVo() {
    }

    public BaseReqVo(String errmsg, int errno) {
        this.errmsg = errmsg;
        this.errno = errno;
    }

    public static BaseReqVo ok(){
        return new BaseReqVo("成功",0);
    }

    public static BaseReqVo ok(Object o){
        return new BaseReqVo(o,"成功",0);
    }
}
