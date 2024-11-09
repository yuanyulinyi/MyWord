package com.yuan.myword.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.myword.constant.MessageConstant;
import com.yuan.myword.exception.AccountNotFoundException;
import com.yuan.myword.exception.PasswordErrorException;
import com.yuan.myword.mapper.LoginMapper;
import com.yuan.myword.mapper.UserMapper;
import com.yuan.myword.pojo.User;
import com.yuan.myword.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        //1、根据用户名查询数据库
        User user = loginMapper.getByUsername(username);
        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // 对前端明文密码进行md5加密，然后再进行比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());//md5加密
        if (!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        //3、返回实体对象
        return user;
    }
    public User findByUsername(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public void register(String username, String password) {
        password = DigestUtils.md5DigestAsHex(password.getBytes());//md5加密密码
        loginMapper.register(username,password);
    }
}
