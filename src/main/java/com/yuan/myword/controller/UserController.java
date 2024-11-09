package com.yuan.myword.controller;

import com.yuan.myword.pojo.User;
import com.yuan.myword.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    //检查用户角色是否包含USER
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/list")
    public List<User> getList(){
        return userService.list();
    }

    //检查用户权限是否包含ROLE_USER，侧面反映了角色本质是权限：ROLE_ + 角色名
    @PreAuthorize("hasAuthority('ROLE_USER') and authentication.name=='admin' ")
    @PostMapping("/add")
    public void add(@RequestBody User user){
        userService.saveUserDetails(user);
    }
}
