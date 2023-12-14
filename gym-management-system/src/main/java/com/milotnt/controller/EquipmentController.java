package com.milotnt.controller;

import com.milotnt.pojo.Equipment;
import com.milotnt.service.EquipmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:8080", "null"})
@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;
    @ApiOperation("显示所有器材")
    @GetMapping("/selEquipment")
    public ResponseEntity<List<Equipment>> selectEquipment() {
        List<Equipment> equipmentList = equipmentService.findAll();
        return ResponseEntity.ok(equipmentList);
    }
    @ApiOperation("删除器材")
    @DeleteMapping("/delEquipment/{equipmentId}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Integer equipmentId) {
        equipmentService.deleteByEquipmentId(equipmentId);
        return ResponseEntity.ok().build();
    }
    @ApiOperation("跳转到更新器材页面")
    @GetMapping("/toUpdateEquipment/{equipmentId}")
    public ResponseEntity<List<Equipment>> toUpdateEquipment(@PathVariable Integer equipmentId) {
        List<Equipment> equipmentList = equipmentService.selectByEquipmentId(equipmentId);
        return ResponseEntity.ok(equipmentList);
    }

    @ApiOperation("更新器材")
    @PutMapping("/updateEquipment")
    public ResponseEntity<Void> updateEquipment(@RequestBody Equipment equipment) {
        equipmentService.updateEquipmentByEquipmentId(equipment);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("跳转到添加器材界面")
    @GetMapping("/toAddEquipment")
    public String toAddEquipment() {
        return "addEquipment";
    }

    @ApiOperation("添加器材")
    @PostMapping("/addEquipment")
    public ResponseEntity<Void> addEquipment(@RequestBody Equipment equipment) {
        equipmentService.insertEquipment(equipment);
        return ResponseEntity.ok().build();
    }
}
