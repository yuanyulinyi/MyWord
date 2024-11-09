package com.yuan.myword.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.myword.pojo.AttendanceInfo;

public interface AttendanceService extends IService<AttendanceInfo> {
    AttendanceInfo getAttendanceInfoByEmployeeId(Integer employeeId);
}
