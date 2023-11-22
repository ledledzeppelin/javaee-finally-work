package com.jianshengfang.demo.dao;

import com.jianshengfang.demo.entity.Adminuser;
import com.jianshengfang.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao extends JpaRepository<Adminuser, Long> {
    // 这里可以添加一些特定的查询方法，根据需要进行定义
    Adminuser findByNameAndPassword(String name, String password);
}
