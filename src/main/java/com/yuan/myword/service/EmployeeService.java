package com.yuan.myword.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.myword.pojo.EmployeeInfo;
import com.yuan.myword.pojo.PageQuery;
import com.yuan.myword.pojo.PageResult;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface EmployeeService extends IService<EmployeeInfo> {
    PageResult selectPages(PageQuery query);

    BigDecimal calcSalary(Integer employeeId);

    List<String> getDept();

    BigDecimal calcSalaryByDept(String dept);

    Map<String, BigDecimal> calcAllDeptSalary();
}
