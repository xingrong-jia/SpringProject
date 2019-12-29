package com.jiaxingrong.exception;

import com.jiaxingrong.model.BaseReqVo;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class CustomExceptionHandler {



    @ExceptionHandler(AuthorizationException.class)
    public BaseReqVo noPermission(){
        BaseReqVo<Object> baseReqVo = new BaseReqVo<>("没有权限",500);
        return baseReqVo;
    }
}
