package com.yuan.myword.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/*@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("salary_info")*/
public class SalaryInfo {
    private Integer salaryId;

    private Integer employeeId;

    private BigDecimal basicSalary;

    private BigDecimal postAllowance;

    private BigDecimal priceSubsidy;

    private BigDecimal positionAllowance;

    private BigDecimal housingSubsidy;

    private BigDecimal rent;
}

