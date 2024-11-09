package com.yuan.myword.mapper;

import com.yuan.myword.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    /**
     * 根据用户名查询员工
     */
    @Select("select * from user where username = #{username}")
    User getByUsername(String username);

    @Insert("insert into user(username,password,role) values(#{username},#{password},'common')")
    void register(String username,String password);
}
