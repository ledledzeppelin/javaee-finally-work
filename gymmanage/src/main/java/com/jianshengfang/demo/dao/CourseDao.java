package com.jianshengfang.demo.dao;

import com.jianshengfang.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDao extends JpaRepository<Course, Integer> {
    // 自定义查询方法
    // ... 可以添加根据需要的查询方法
}
