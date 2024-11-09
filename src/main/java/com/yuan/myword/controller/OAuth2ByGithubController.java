package com.yuan.myword.controller;

import com.yuan.myword.pojo.Result;
import com.yuan.myword.pojo.User;
import com.yuan.myword.properties.JwtProperties;
import com.yuan.myword.service.GitHubService;
import com.yuan.myword.service.LoginService;
import com.yuan.myword.utils.JwtUtil;
import com.yuan.myword.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login/oauth2")
public class OAuth2ByGithubController {
    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private LoginService loginService;

    @Autowired
    private GitHubService gitHubService;

    @Value("${spring.security.oauth2.client.registration.github.client-id}")
    private String githubClientId;

    @GetMapping("/github")
    public Result authByGithub(){
        System.out.println(githubClientId);
        return Result.success(githubClientId);
    }

    @GetMapping("/code/github")
    public Result callbackByGithub(@RequestParam("code") String code) {
        String accessToken = gitHubService.getAccessToken(code);
        Map<String, Object> userInfo = gitHubService.getUserInfo(accessToken);

        String name = (String) userInfo.get("login");
        Long id = ((Number) userInfo.get("id")).longValue();
        String avatarUrl = (String) userInfo.get("avatar_url");

        System.out.println("User Name: " + name);
        System.out.println("User ID: " + id);
        System.out.println("Avatar URL: " + avatarUrl);

        // Check if user already exists
        User existingUser = loginService.findByUsername(name);
        if (existingUser == null) {
            // Register the new user if not existing
            loginService.register(name, "123456");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", existingUser.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        UserVO userVO = UserVO.builder()
                .id(existingUser.getId())
                .userName(existingUser.getUsername())
                .token(token)
                .build();

        return Result.success(userVO);
    }

}
