package com.yuan.myword.pojo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@ColumnWidth(16)
@HeadRowHeight(14)
@HeadFontStyle(fontHeightInPoints = 11)
@ExcelIgnoreUnannotated
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payroll {

    private Integer payrollId;

    @NumberFormat("#")
    @ExcelProperty("员工编号")
    private Integer employeeId;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "日期")
    private Date monthYear;

    @NumberFormat("#,###.00")
    @ExcelProperty(value = "基本工资")
    private BigDecimal basicSalary;

    @NumberFormat("#,###.00")
    @ExcelProperty(value = "岗位津贴")
    private BigDecimal postAllowance;

    @NumberFormat("#,###.00")
    @ExcelProperty(value = "物价补贴")
    private BigDecimal priceSubsidy;

    @NumberFormat("#,###.00")
    @ExcelProperty(value = "职务津贴")
    private BigDecimal positionAllowance;

    @NumberFormat("#,###.00")
    @ExcelProperty(value = "住房补贴")
    private BigDecimal housingSubsidy;

    @NumberFormat("#,###.00")
    @ExcelProperty(value = "加班费")
    private BigDecimal overtimePay;

    @NumberFormat("#,###.00")
    @ExcelProperty(value = "夜班费")
    private BigDecimal nightShiftPay;

    @NumberFormat("#,###.00")
    @ExcelProperty(value = "房租")
    private BigDecimal rent;

    @NumberFormat("#,###.00")
    @ExcelProperty(value = "病假扣款")
    private BigDecimal sickDeduction;

    @NumberFormat("#,###.00")
    @ExcelProperty(value = "事假扣款")
    private BigDecimal leaveDeduction;

    @NumberFormat("#,###.00")
    @ExcelProperty(value = "旷工扣款")
    private BigDecimal absenceDeduction;

    @NumberFormat("#,###.00")
    @ExcelProperty(value = "其他扣款")
    private BigDecimal otherDeductions;

    @NumberFormat("#,###.00")
    @ExcelProperty(value = "实发工资")
    private BigDecimal actualSalary;
}