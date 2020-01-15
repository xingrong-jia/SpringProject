package com.stylefeng.guns.user;

import com.stylefeng.guns.user.vo.UserInfoVo;
import com.stylefeng.guns.user.vo.UserRegisterVo;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-08 15:23
 */
public interface UserService {
    Boolean queryUserNameIsExist(String username);

    int addUser(UserRegisterVo userRegisterVo);

    int modifyUserInformation(UserInfoVo userInfoVo);

    Boolean validate(String userName, String password);

    UserInfoVo getUserInfo(String username);

    Integer queryUserId(String username);
}
