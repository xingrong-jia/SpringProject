package com.stylefeng.guns.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-08 15:49
 */
@Data
public class UserRegisterVo implements Serializable {



    String username;

    String password;

    String email;

    String mobile;

    String address;

}
