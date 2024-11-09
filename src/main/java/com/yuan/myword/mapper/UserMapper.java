package com.yuan.myword.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.myword.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
