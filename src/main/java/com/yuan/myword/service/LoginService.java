package com.yuan.myword.service;

import com.yuan.myword.pojo.User;

public interface LoginService {
    User login(String username, String password);
    void register(String username, String password);
    public User findByUsername(String username);
}
