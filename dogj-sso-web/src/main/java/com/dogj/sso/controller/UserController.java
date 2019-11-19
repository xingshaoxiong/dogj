package com.dogj.sso.controller;

import com.dogj.common.pojo.DogjResult;
import com.dogj.common.utils.CookieUtils;
import com.dogj.pojo.DogjUser;
import com.dogj.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;

    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public DogjResult checkUserData(@PathVariable String param, @PathVariable Integer type) {
        DogjResult result = userService.checkData(param, type);
        return result;
    }

    @RequestMapping(value = "user/register", method = RequestMethod.POST)
    @ResponseBody
    public DogjResult register(@RequestBody DogjUser user) {
        DogjResult result = userService.register(user);
        return result;
    }

    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    @ResponseBody
    public DogjResult login(String username, String password,
                            HttpServletResponse response, HttpServletRequest request) {
        DogjResult result = userService.login(username, password);
        if (result.getStatus() == 200) {
            CookieUtils.setCookie(request, response, TOKEN_KEY, result.getData().toString());
        }
        return result;
    }

    @RequestMapping(value="/user/token/{token}", method=RequestMethod.GET)
    @ResponseBody
    public Object getUserByToken(@PathVariable String token, String callback) {
        DogjResult result = userService.getUserByToken(token);
        //判断是否为jsonp请求
        if (StringUtils.isNotBlank(callback)) {
           return null;
        }
        return result;
    }
}
