package com.yuan.myword.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.myword.mapper.UserMapper;
import com.yuan.myword.pojo.User;
import com.yuan.myword.security.DBUserDetailsManager;
import com.yuan.myword.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private DBUserDetailsManager userDetailsManager;

    @Override
    public void saveUserDetails(User user) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withDefaultPasswordEncoder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        userDetailsManager.createUser(userDetails);
    }
}
