package com.milotnt.controller;

import com.milotnt.pojo.ClassOrder;
import com.milotnt.pojo.ClassTable;
import com.milotnt.service.ClassOrderService;
import com.milotnt.service.ClassTableService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author MiloTnT [milotntspace@gmail.com]
 * @date 2021/8/19
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassTableService classTableService;

    @Autowired
    private ClassOrderService classOrderService;

    // 查询课程
    @ApiOperation("查询课程")
    @GetMapping("/selClass")
    public ResponseEntity<List<ClassTable>> selectClass() {
        // 获取所有课程列表
        List<ClassTable> classList = classTableService.findAll();

        // 返回课程列表
        return ResponseEntity.ok(classList);
    }

    @ApiOperation("新增课程")
    @PostMapping("/addClass")
    public ResponseEntity<String> addClass(@RequestBody ClassTable classTable) {
        // 调用服务层方法添加课程
        classTableService.insertClass(classTable);
        String responseBody="添加课程成功";

        // 返回新增的课程信息
        return ResponseEntity.ok(responseBody);
    }

    @ApiOperation("删除课程")
    @DeleteMapping("/delClass/{classId}")
    public ResponseEntity<Void> deleteClass(@PathVariable Integer classId) {
        // 调用服务层方法删除课程
        classTableService.deleteClassByClassId(classId);

        // 同时删除相关的课程报名信息
        classOrderService.deleteByClassOrderId(classId);

        // 返回空响应
        return ResponseEntity.ok().build();
    }

    @ApiOperation("查询课程报名信息")
    @GetMapping("/selClassOrder/{classId}")
    public ResponseEntity<List<ClassOrder>> selectClassOrder(@PathVariable Integer classId) {
        // 根据课程ID查询相关的课程报名信息
        List<ClassOrder> classOrderList = classOrderService.selectMemberOrderList(classId);

        // 返回课程报名信息列表
        return ResponseEntity.ok(classOrderList);
    }
}
