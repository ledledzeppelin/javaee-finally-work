package com.jianshengfang.demo.service;

import com.jianshengfang.demo.dao.EquipmentDao;
import com.jianshengfang.demo.dao.UserDao;
import com.jianshengfang.demo.entity.Equipment;
import com.jianshengfang.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentDao equipmentRepository;

    @Autowired
    LogService logService;
    @Autowired
    private UserDao userDao;

    public List<Equipment> getAllAvailableEquipment() {
        return equipmentRepository.findByIsBorrowedFalse();
    }

    public List<Equipment> getReservedEquipmentByUserId(int userId) {
        return equipmentRepository.findByUserId(userId);
    }
    // 预约设备并添加日志
    public Equipment reserveEquipment(long userId, int equipmentId, LocalDateTime startTime, LocalDateTime endTime) {
        Equipment equipment = equipmentRepository.findById(equipmentId);

            if (!equipment.isBorrowed()) {
                equipment.setBorrowed(true);
                User user=userDao.findById(userId).orElse(null);
                equipment.setUser(user);
                equipment.setStartTime(startTime); // 设置预约开始时间
                equipment.setEndTime(endTime);     // 设置预约结束时间
                String actionDescription = null;
                if (user != null) {
                    actionDescription = user.getName() + "在（" + startTime + " - " + endTime + equipment.getName()+
                            "健身";
                }
                logService.recordUserAction(user, actionDescription);


                return equipmentRepository.save(equipment);
            }

        return null; // 或者抛出异常
    }

    // 归还设备
    public void returnEquipment(int equipmentId) {
        Equipment equipment = equipmentRepository.findById(equipmentId);
        if (equipment != null && equipment.isBorrowed()) {
            equipment.setBorrowed(false);
            equipment.setUser(null);
            equipmentRepository.save(equipment);
        } else {
            // 处理设备未被预约或已归还的情况
        }
    }

    // 其他设备服务方法
}

