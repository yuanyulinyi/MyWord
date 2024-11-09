package com.yuan.myword;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yuan.myword.mapper")
public class MyWordApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyWordApplication.class, args);
    }

}
