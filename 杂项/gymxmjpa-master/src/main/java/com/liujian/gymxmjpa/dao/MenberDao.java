package com.liujian.gymxmjpa.dao;


import com.liujian.gymxmjpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 会员信息Dao层接口
 */
public interface MenberDao extends JpaRepository<Member,Long> {
}
