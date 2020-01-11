package com.stylefeng.guns.rest.modular.auth.filter;

import com.jiaxingrong.utils.StringTool;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.util.RenderUtil;
import com.stylefeng.guns.rest.common.exception.BizExceptionEnum;
import com.stylefeng.guns.rest.config.properties.JwtProperties;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 对客户端请求的jwt token验证过滤器
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:04
 */

public class AuthFilter extends OncePerRequestFilter {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;


    private List<String> urlList ;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        urlList = jwtProperties.getUrlList();
        String uri = request.getRequestURI();
        if (!uri.contains("/alipay/")&&!urlList.contains(uri)&&!uri.contains(urlList.get(0))) {
            final String requestHeader = request.getHeader(jwtProperties.getHeader());
            String authToken = null;
            if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
                authToken = requestHeader.substring(7);
                jwtTokenUtil.parseToken(authToken);//如果token不正确，会报JwtException异常，且被异常处理器处理

                String username = jwtTokenUtil.getUsernameFromToken(authToken);

                String redisToken = (String) redisTemplate.opsForValue().get(username);
                if (!StringTool.isNotNull(redisToken)) return;
                redisTemplate.opsForValue().set(username,authToken,1800, TimeUnit.SECONDS);
            }else return;
        }
        chain.doFilter(request, response);
    }
}