package com.liujian.gymxmjpa.dao;


import com.liujian.gymxmjpa.entity.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 教练信息Dao层接口
 */
public interface CoachDao extends JpaRepository<Coach,Long> {
}
