package com.yuan.myword.security;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.HashMap;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Object principal = authentication.getPrincipal();//获取用户身份信息
        /*Object credentials = authentication.getCredentials();//获取用户凭证信息
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();//获取用户权限信息*/

        HashMap<String,Object> res = new HashMap<>();
        res.put("code",200);//状态码
        res.put("msg","登录成功");//状态信息
        res.put("data",principal);///返回用户信息

        String json = JSON.toJSONString(res);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
