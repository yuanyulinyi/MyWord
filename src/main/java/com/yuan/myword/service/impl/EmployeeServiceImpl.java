package com.yuan.myword.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.yuan.myword.mapper.EmployeeMapper;
import com.yuan.myword.mapper.PayrollMapper;
import com.yuan.myword.pojo.*;
import com.yuan.myword.service.AttendanceService;
import com.yuan.myword.service.EmployeeService;
import com.yuan.myword.service.PayrollService;
import com.yuan.myword.service.SalaryService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, EmployeeInfo> implements EmployeeService {
    @Resource
    private SalaryService salaryService;
    @Resource
    private AttendanceService attendanceService;
    @Resource
    private PayrollService payrollService;
    @Resource
    private PayrollMapper payrollMapper;
    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    public PageResult selectPages(PageQuery query) {
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        List<EmployeeInfo> list = employeeMapper.selectList(null);//内置查询条件
        return new PageResult(
                list.size(),
                list.subList(
                        (query.getPageNum()-1)*query.getPageSize()
                        ,Math.min(list.size(),query.getPageNum()*query.getPageSize())));
    }

    @Override
    public BigDecimal calcSalary(Integer employeeId) {
        // 先从 payroll 表中查询，如果有记录，则直接返回
        Payroll payroll = payrollService.getPayrollByEmployeeId(employeeId);
        if (payroll != null) {
            return payroll.getActualSalary();
        }
        // 没有该记录，则从 salary 表中查询
        payroll = new Payroll();
        SalaryInfo salaryInfo = salaryService.getSalaryInfoByEmployeeId(employeeId);
        AttendanceInfo attendanceInfo = attendanceService.getAttendanceInfoByEmployeeId(employeeId);
        BeanUtils.copyProperties(salaryInfo, payroll);
        BeanUtils.copyProperties(attendanceInfo, payroll);
        System.out.println(payroll);
        //设置月份
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();

        // 将 LocalDate 转换为 java.util.Date
        Date monthYear = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // 设置 payroll 的 monthYear 字段
        payroll.setMonthYear(monthYear);

        //设置overtimePay,nightShiftPay,sickDeduction,leaveDeduction,absenceDeduction,otherDeductions
        BigDecimal daySalary = salaryInfo.getBasicSalary().divide(new BigDecimal("30"), 2, BigDecimal.ROUND_HALF_UP);

        payroll.setOvertimePay(daySalary.multiply(BigDecimal.valueOf(attendanceInfo.getOvertimeDays())));
        payroll.setNightShiftPay(daySalary.multiply(new BigDecimal(attendanceInfo.getNightShiftDays())));
        payroll.setSickDeduction(daySalary.multiply(new BigDecimal(attendanceInfo.getSickDays())));
        payroll.setLeaveDeduction(daySalary.multiply(new BigDecimal(attendanceInfo.getLeaveDays())));
        payroll.setAbsenceDeduction(daySalary.multiply(new BigDecimal(attendanceInfo.getAbsentDays())));
        payroll.setOtherDeductions(new BigDecimal(0));

        //计算actualSalary
        payroll.setActualSalary(
                payroll.getBasicSalary()
                .add(payroll.getPostAllowance())
                .add(payroll.getPriceSubsidy())
                .add(payroll.getPositionAllowance())
                .add(payroll.getHousingSubsidy())
                .add(payroll.getOvertimePay())
                .add(payroll.getNightShiftPay())
                .add(payroll.getRent())
                .subtract(payroll.getSickDeduction())
                .subtract(payroll.getLeaveDeduction())
                .subtract(payroll.getAbsenceDeduction())
                .subtract(payroll.getOtherDeductions()));

        payrollMapper.insert(payroll);
        return payroll.getActualSalary();
    }

    @Override
    public List<String> getDept() {
        return employeeMapper.getDept();
    }

    @Override
    public BigDecimal calcSalaryByDept(String dept) {
        return employeeMapper.calcSalaryByDept(dept);
    }

    @Override
    public Map<String, BigDecimal> calcAllDeptSalary() {
        return employeeMapper.calcAllDeptSalary();
    }
}
