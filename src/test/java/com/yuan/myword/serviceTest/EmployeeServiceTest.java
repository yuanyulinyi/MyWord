package com.yuan.myword.serviceTest;
import com.yuan.myword.pojo.PageQuery;
import com.yuan.myword.service.impl.EmployeeServiceImpl;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class EmployeeServiceTest {
    @Resource
    EmployeeServiceImpl employeeService ;
    @Test
    public void selectPageTest() {
        PageQuery query = new PageQuery();
        query.setPageNum(1);
        query.setPageSize(5);
        System.out.println(employeeService.selectPages(query));
    }

    @Test
    public void calcSalaryTest() {
        System.out.println(employeeService.calcSalary(1));
    }

    @Test
    public void calcAllDeptSalaryTest(){
        HashMap map = new HashMap(employeeService.calcAllDeptSalary());
        for(Object o : map.keySet()){
            System.out.println(o + ":" + map.get(o));
        }
        System.out.println(map);
    }

    @Test
    public void getSalaryByDeptTest(){
        System.out.println(employeeService.calcSalaryByDept("研发部"));
    }
}
