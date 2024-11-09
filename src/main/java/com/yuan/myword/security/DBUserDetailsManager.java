package com.yuan.myword.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuan.myword.mapper.UserMapper;
import com.yuan.myword.pojo.User;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    /**
     * 向数据库中创建新的用户
     * @param userDetails
     */
    @Override
    public void createUser(UserDetails userDetails) {
        User user = new User();
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        userMapper.insert(user);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .disabled(false)
                    .credentialsExpired(false)
                    .accountExpired(false)
                    .accountLocked(false)
                    .roles("USER")//通常是从数据库中查询，这里为了方便调试直接写死 TODO
                    .build();
        }
    }
}
