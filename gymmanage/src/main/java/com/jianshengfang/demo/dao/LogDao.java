package com.jianshengfang.demo.dao;

import com.jianshengfang.demo.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDao extends JpaRepository<Log, Long> {
    // 可能有一些自定义的查询方法，例如根据用户查找日志等
    // 如果有自定义查询需求，您可以根据需要在这里定义方法
}