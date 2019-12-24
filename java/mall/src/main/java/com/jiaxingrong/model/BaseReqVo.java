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
}
