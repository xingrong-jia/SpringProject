package com.jiaxingrong.shiro;

import com.jiaxingrong.utils.StringTool;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class CustomSessionManager extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest servletRequest, ServletResponse response) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String header = request.getHeader("X-Litemall-Admin-Token");
        if (StringTool.isNotNull(header)){
            return header;
        }

        return super.getSessionId(request, response);
    }
}
