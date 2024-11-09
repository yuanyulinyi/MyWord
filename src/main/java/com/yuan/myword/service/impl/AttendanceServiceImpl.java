package com.yuan.myword.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuan.myword.mapper.AttendanceMapper;
import com.yuan.myword.pojo.AttendanceInfo;
import com.yuan.myword.service.AttendanceService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, AttendanceInfo> implements AttendanceService {
    @Resource
    private AttendanceMapper attendanceMapper;
    @Override
    public AttendanceInfo getAttendanceInfoByEmployeeId(Integer employeeId) {
        QueryWrapper<AttendanceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("employee_id", employeeId);
        return attendanceMapper.selectOne(queryWrapper);
    }
}
