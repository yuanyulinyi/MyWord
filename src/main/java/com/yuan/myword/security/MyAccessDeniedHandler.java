package com.yuan.myword.security;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.util.HashMap;

public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        HashMap<String,Object> res = new HashMap<>();
        res.put("code",403);
        res.put("msg","权限不足");
        res.put("data",null);

        String json = JSON.toJSONString(res);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
