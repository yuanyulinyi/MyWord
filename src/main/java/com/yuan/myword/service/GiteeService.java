package com.yuan.myword.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.Map;

@Service
public class GiteeService {

    @Value("${login.gitee.clientId}")
    private String clientId;

    @Value("${login.gitee.clientSecret}")
    private String clientSecret;

    public String getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromUriString("https://gitee.com/oauth/token")
                .queryParam("grant_type", "authorization_code")
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("code", code)
                .queryParam("redirect_uri", "http://localhost:5173/login/oauth2/code/gitee") // 替换为实际的回调地址
                .build()
                .toString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
        return response.getBody().get("access_token").toString();
    }

    public Map<String, Object> getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                "https://gitee.com/api/v5/user",
                HttpMethod.GET,
                entity,
                Map.class
        );

        return response.getBody();
    }
}