package com.yuan.myword.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.myword.pojo.Payroll;

public interface PayrollService extends IService<Payroll> {
    Payroll getPayrollByEmployeeId(Integer employeeId);
}
