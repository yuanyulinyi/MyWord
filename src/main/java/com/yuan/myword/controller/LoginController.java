package com.yuan.myword.controller;

import com.yuan.myword.pojo.Result;
import com.yuan.myword.pojo.User;
import com.yuan.myword.properties.JwtProperties;
import com.yuan.myword.service.LoginService;
import com.yuan.myword.utils.JwtUtil;
import com.yuan.myword.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtProperties jwtProperties;
    @PostMapping("/login")
    public Result login(@RequestBody User u){
        User user = loginService.login(u.getUsername(),u.getPassword());
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        UserVO userVO = UserVO.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .token(token)
                .build();

        return Result.success(userVO);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User u){
        loginService.register(u.getUsername(), u.getPassword());
        return Result.success();
    }
}
