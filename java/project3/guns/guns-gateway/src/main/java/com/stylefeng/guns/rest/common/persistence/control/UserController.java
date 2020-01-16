package com.stylefeng.guns.rest.common.persistence.control;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jiaxingrong.utils.StringTool;
import com.stylefeng.guns.rest.config.properties.JwtProperties;
import com.stylefeng.guns.rest.model.Result;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.user.UserService;
import com.stylefeng.guns.user.vo.UserInfoVo;
import com.stylefeng.guns.user.vo.UserRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-08 15:25
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Reference(interfaceClass = UserService.class, retries = 1)
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("register")
    public Result register(UserRegisterVo userRegisterVo) {

        Boolean userNameIsExist = userService.queryUserNameIsExist(userRegisterVo.getUsername());
        if (userNameIsExist) {
            return new Result(1, "该用户名已存在，请修改！");
        }
        int add = userService.addUser(userRegisterVo);
        if (add == 1) return Result.ok("注册成功!");
        return new Result(999, "系统出现异常，请联系管理员!");
    }

    @RequestMapping("logout")
    public Result logout(@RequestHeader String Authorization) {
        String header = Authorization;
        if (StringTool.isNotNull(header)) {
            String authToken = "";
            if (header.startsWith("Bearer ")) {
                authToken = header.substring(7);
                String username = jwtTokenUtil.getUsernameFromToken(authToken);
                Object o = redisTemplate.opsForValue().get(username);
                if (o != null) {
                    Boolean delete = redisTemplate.delete(username);
                    if (delete) return Result.ok("成功退出！");
                    else return Result.failure();
                }
            }
        }
        return new Result(700, "查询失败，用户尚未登陆");
    }

    @RequestMapping("check")
    public Result check(String username) {
        Boolean userNameIsExist = userService.queryUserNameIsExist(username);
        if (userNameIsExist) {
            return new Result(1, "用户已经注册");
        }
        if (!userNameIsExist) {
            return Result.ok("用户名不存在");
        }
        return new Result(999, "系统出现异常，请联系管理员!");
    }

    @RequestMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String Authorization) {
        String header = Authorization;
        if (StringTool.isNotNull(header)) {
            String authToken = null;
            if (header.startsWith("Bearer ")) {
                authToken = header.substring(7);
                String username = jwtTokenUtil.getUsernameFromToken(authToken);
                UserInfoVo userInfoVo = userService.getUserInfo(username);
                if (userInfoVo != null) {
                    return Result.ok(userInfoVo);
                } else return Result.failure();
            }
        }
        return new Result(700, "查询失败，用户尚未登陆");
    }

    @RequestMapping("updateUserInfo")
    public Result updateUserInfo(UserInfoVo userInfoVo) {
        int modifyUser = userService.modifyUserInformation(userInfoVo);
        if (modifyUser == 1) return Result.ok("修改资料成功！", userInfoVo);
        else if (modifyUser == 0) return new Result(1, "用户信息修改失败!");
        else return Result.failure();
    }

}
