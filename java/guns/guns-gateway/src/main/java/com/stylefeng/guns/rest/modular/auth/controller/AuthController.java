package com.stylefeng.guns.rest.modular.auth.controller;

import com.stylefeng.guns.rest.model.Result;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthRequest;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthResponse;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.auth.validator.IReqValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 请求验证的
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:22
 */
@RestController
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource(name = "simpleValidator")
    private IReqValidator reqValidator;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "${jwt.auth-path}")
    public Result createAuthenticationToken(AuthRequest authRequest) {

        boolean validate = reqValidator.validate(authRequest);

        if (validate) {
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(authRequest.getUserName(), randomKey);
            redisTemplate.opsForValue().set(authRequest.getUserName(), token,1800, TimeUnit.SECONDS);
            return Result.ok(new AuthResponse(token, randomKey));
        }
        if (!validate){
            return new Result(1,"用户名或密码错误,请重试！");
        }
        return new Result(999,"系统出现异常，请联系管理员！");
    }
}
