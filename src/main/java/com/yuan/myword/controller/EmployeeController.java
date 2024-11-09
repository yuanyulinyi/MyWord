package com.yuan.myword.controller;

import com.yuan.myword.mapper.PayrollMapper;
import com.yuan.myword.pojo.*;
import com.yuan.myword.service.EmployeeService;
import com.yuan.myword.utils.ExcelUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/salary")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;

    @Resource
    private PayrollMapper payrollMapper;

    /**
     * 导出工资计算表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response) throws UnsupportedEncodingException {
        // 查询所有工资记录
        List<Payroll> list = payrollMapper.selectList(null);
        System.out.println(list);

        // 设置响应头
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        String fileName = URLEncoder.encode("工资统计表_" + System.currentTimeMillis() + ".xlsx", "UTF-8").replace("+", "%20");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        // 创建 Excel 工具类并导出 Excel 文件
        ExcelUtil<Payroll> util = new ExcelUtil<>(Payroll.class);
        util.exportEasyExcel(response, list, "工资统计表");
    }

    @PostMapping("/query")
    public Result<PageResult> queryEmployee(@RequestBody PageQuery query){
        return Result.success(employeeService.selectPages(query));
    }

    @GetMapping("/calc/{employeeId}")
    public Result<BigDecimal> calcSalary(@PathVariable Integer employeeId){
        return Result.success(employeeService.calcSalary(employeeId));
    }

    @GetMapping("/calc/dept")
    public Result<Map<String, BigDecimal>> calcAllDeptSalary(){
        return Result.success(employeeService.calcAllDeptSalary());
    }

    @GetMapping("/dept/list")
    public Result<List<String>> getDept() {
        return Result.success(employeeService.getDept());
    }

    @GetMapping("/dept/calc/{dept}")
    public Result<BigDecimal> getSalaryByDept(@PathVariable String dept){
        return Result.success(employeeService.calcSalaryByDept(dept));
    }
}
