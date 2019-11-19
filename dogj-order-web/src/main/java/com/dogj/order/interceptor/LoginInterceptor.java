package com.dogj.order.interceptor;

import com.dogj.common.pojo.DogjResult;
import com.dogj.common.utils.CookieUtils;
import com.dogj.pojo.DogjUser;
import com.dogj.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;
    @Autowired
    private UserService userService

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = CookieUtils.getCookieValue(httpServletRequest, TOKEN_KEY);
        if (StringUtils.isBlank(token)) {
            return false;
        }
        DogjResult dogjResult = userService.getUserByToken(token);
        if (dogjResult.getStatus() != 200) {
            return false;
        }
        DogjUser user = (DogjUser) dogjResult.getData();
        httpServletRequest.setAttribute("user", user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
