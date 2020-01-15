package com.stylefeng.guns;

import com.jiaxingrong.utils.JsonUtils;
import com.jiaxingrong.utils.StringTool;
import com.stylefeng.guns.user.vo.UserRegisterVo;
import org.junit.Test;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-08 16:28
 */

public class UserTest {

    @Test
    public void mytest1(){
        UserRegisterVo userRegisterVo = new UserRegisterVo();
        userRegisterVo.setUsername("aaa");
        userRegisterVo.setPassword("aaa");
        userRegisterVo.setEmail("aaa@qq.com");
        userRegisterVo.setAddress("bbb");
        userRegisterVo.setMobile("123456789");
        String s = JsonUtils.convertToJson(userRegisterVo);
        System.out.println(s);
    }
}
