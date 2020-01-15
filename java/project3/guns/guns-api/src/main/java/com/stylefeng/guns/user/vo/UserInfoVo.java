package com.stylefeng.guns.user.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-08 19:22
 */
@Data
public class UserInfoVo  implements Serializable {

    private Integer uuid;

    private String username;

    private String nickname;

    private String email;

    private String phone;

    private Integer sex;

    private String birthday;

    private Integer lifeState;

    private String biography;

    private String address;

    private Date updateTime;

    private String headAddress;
}
