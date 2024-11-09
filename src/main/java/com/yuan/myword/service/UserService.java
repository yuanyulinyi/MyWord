package com.yuan.myword.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.myword.pojo.User;

public interface UserService extends IService<User> {
    void saveUserDetails(User user);
}
