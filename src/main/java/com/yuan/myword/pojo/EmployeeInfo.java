package com.yuan.myword.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("employee_info")
public class EmployeeInfo {
    @TableId(type = IdType.AUTO)
    private Integer employeeId;

    private String code;

    private String name;

    private Gender gender;

    private Date birthDate;

    private Date joinDate;

    private String department;

    private String category;

    private String position;

    private String technicalLevel;

    private String technicalTitle;

    private String education;

    private String ethnicity;

    private String nativePlace;

    private String address;

    public enum Gender {
        男, 女
    }
}

