package com.yuan.myword.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.myword.mapper.PayrollMapper;
import com.yuan.myword.pojo.Payroll;
import com.yuan.myword.service.PayrollService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PayrollServiceImpl extends ServiceImpl<PayrollMapper, Payroll> implements PayrollService {
    @Resource
    private PayrollMapper payrollMapper;

    @Override
    public Payroll getPayrollByEmployeeId(Integer employeeId) {
        // 使用 QueryWrapper
        QueryWrapper<Payroll> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employee_id", employeeId);

        // 执行查询
        return payrollMapper.selectOne(queryWrapper);
    }
}
