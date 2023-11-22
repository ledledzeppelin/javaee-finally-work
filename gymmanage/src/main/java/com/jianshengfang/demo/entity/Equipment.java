package com.jianshengfang.demo.entity;



import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Description: 管理员信息实体类
 */
@Entity      //标识一个 Java 类作为 JPA 的实体类，表明该类的对象可以映射到数据库中的表。
@Table(name = "equipments")
@Data
public class Equipment {
    @Id                         //标识实体类中作为数据库表主键的属性
    @GeneratedValue(strategy = GenerationType.AUTO)   //主键的生成策略为自动选择适合数据库的主键生成策略。

        private int equipmentId;
        private String name;
        private String description;
        private int count;
        private boolean isBorrowed;
        private LocalDateTime startTime; // 预约开始时间属性
        private LocalDateTime endTime;
    // Other relevant attributes
        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;



}