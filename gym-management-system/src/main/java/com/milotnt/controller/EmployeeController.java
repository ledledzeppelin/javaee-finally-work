package com.milotnt.controller;

import com.milotnt.pojo.Employee;
import com.milotnt.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = {"http://localhost:8080", "null"})
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation("查询员工")
    @GetMapping("/selEmployee")
    public ResponseEntity<List<Employee>> selectEmployee() {
        List<Employee> employeeList = employeeService.findAll();
        return ResponseEntity.ok(employeeList);
    }

    @ApiOperation("跳转新增员工页面")
    @GetMapping("/toAddEmployee")
    public String toAddEmployee() {
        return "addEmployee";
    }

    @ApiOperation("新增员工")
    @PostMapping("/addEmployee")
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {
        // 工号随机生成
        Random random = new Random();
        String account1 = "1010";
        for (int i = 0; i < 5; i++) {
            account1 += random.nextInt(10);
        }
        Integer account = Integer.parseInt(account1);

        // 获取当前日期
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDay = simpleDateFormat.format(date);

        employee.setEmployeeAccount(account);
        employee.setEntryTime(nowDay);

        employeeService.insertEmployee(employee);

        return ResponseEntity.ok().build();
    }

    @ApiOperation("删除员工")
    @DeleteMapping("/delEmployee/{employeeAccount}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer employeeAccount) {
        employeeService.deleteByEmployeeAccount(employeeAccount);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("跳转员工修改页面")
    @GetMapping("/toUpdateEmployee/{employeeAccount}")
    public ResponseEntity<List<Employee>> toUpdateEmployee(@PathVariable Integer employeeAccount) {
        List<Employee> employeeList = employeeService.selectByEmployeeAccount(employeeAccount);
        return ResponseEntity.ok(employeeList);
    }

    @ApiOperation("修改员工信息")
    @PutMapping("/updateEmployee")
    public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee) {
        employeeService.updateMemberByEmployeeAccount(employee);
        return ResponseEntity.ok().build();
    }
}

