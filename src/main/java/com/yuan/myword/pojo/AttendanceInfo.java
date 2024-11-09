package com.yuan.myword.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("attendance_info")
public class AttendanceInfo {

    private Integer attendanceId;

    private Integer employeeId;

    private Integer attendanceDays;

    private Integer injuryDays;

    private Integer maternityDays;

    private Integer sickDays;

    private Integer absentDays;

    private Integer leaveDays;

    private Integer overtimeDays;

    private Integer nightShiftDays;

    private BigDecimal sickCoefficient;
}

