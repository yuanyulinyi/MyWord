package com.yuan.myword.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuan.myword.pojo.EmployeeInfo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmployeeMapper extends BaseMapper<EmployeeInfo> {

    @Select("select distinct department from employee_info")
    List<String> getDept();

    BigDecimal calcSalaryByDept(String dept);

    @MapKey("department")
    Map<String, BigDecimal> calcAllDeptSalary();
}
