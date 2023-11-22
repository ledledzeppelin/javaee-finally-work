package com.jianshengfang.demo.controller;

import com.jianshengfang.demo.entity.Equipment;
import com.jianshengfang.demo.entity.User;
import com.jianshengfang.demo.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    //查看能够借的器材
    @GetMapping("/available")
    public ResponseEntity<List<Equipment>> getAllAvailableEquipment() {
        List<Equipment> availableEquipment = equipmentService.getAllAvailableEquipment();
        return ResponseEntity.ok(availableEquipment);
    }

    //查看已预约的器材
    @GetMapping("/reserved/{userId}")
    public ResponseEntity<List<Equipment>> getReservedEquipmentByUserId(@PathVariable int userId) {
        List<Equipment> reservedEquipment = equipmentService.getReservedEquipmentByUserId(userId);
        return ResponseEntity.ok(reservedEquipment);
    }
    // 预约设备的接口
    @PostMapping("/reserve/{equipmentId}")
    //ResponseEntity<String>返回状态码为 xxxx，并带有字符串 <string> 作为响应体
    //@PathVariable用于从 URL 中提取参数值
    //@RequestBody 用于从请求体中获取数据并映射到reserveEquipment上
    public ResponseEntity<String> reserveEquipment(@PathVariable int equipmentId,
                                                   @RequestBody long userId,
                                                   @RequestParam LocalDateTime startTime,
                                                   @RequestParam LocalDateTime endTime) {
        Equipment result = equipmentService.reserveEquipment(userId, equipmentId, startTime, endTime);

        if (result != null) {
            return ResponseEntity.ok("Equipment reserved successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("null");
        }
    }

        // 归还设备的接口
    @PostMapping("/return/{equipmentId}")
    public ResponseEntity<String> returnEquipment(@PathVariable int equipmentId) {
        equipmentService.returnEquipment(equipmentId);
        return ResponseEntity.ok("Equipment returned successfully");
    }

    // 其他设备控制器方法
}

