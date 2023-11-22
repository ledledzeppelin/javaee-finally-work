package com.jianshengfang.demo.dao;

import com.jianshengfang.demo.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentDao extends JpaRepository<Equipment, Long> {

    List<Equipment> findByIsBorrowedFalse();

    List<Equipment> findByUserId(int userId);


    Equipment findById(int equipmentId);

    List<Equipment> findByIsborrowed(boolean isBorrowed);

    // 其他可能的设备操作方法
}

