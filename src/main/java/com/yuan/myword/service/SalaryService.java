package com.yuan.myword.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.myword.pojo.SalaryInfo;

public interface SalaryService extends IService<SalaryInfo> {
    SalaryInfo getSalaryInfoByEmployeeId(Integer employeeId);
}
