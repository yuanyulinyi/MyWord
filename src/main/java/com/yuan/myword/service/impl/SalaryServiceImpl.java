package com.yuan.myword.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.myword.mapper.SalaryMapper;
import com.yuan.myword.pojo.SalaryInfo;
import com.yuan.myword.service.SalaryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, SalaryInfo> implements SalaryService {
    @Resource
    private SalaryMapper salaryMapper;
    @Override
    public SalaryInfo getSalaryInfoByEmployeeId(Integer employeeId) {
        QueryWrapper<SalaryInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employee_id", employeeId);

        // 执行查询
        return salaryMapper.selectOne(queryWrapper);
    }
}
