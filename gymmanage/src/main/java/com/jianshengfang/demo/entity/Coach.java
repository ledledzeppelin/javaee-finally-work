package com.jianshengfang.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity      //标识一个 Java 类作为 JPA 的实体类，表明该类的对象可以映射到数据库中的表。
@Table(name = "coaches")
@Data
public class Coach {
    @Id                         //标识实体类中作为数据库表主键的属性
    @GeneratedValue(strategy = GenerationType.AUTO)   //主键的生成策略为自动选择适合数据库的主键生成策略。

    private int coachId;
    private String name;
    private String account;
    private String password;
    private String specialization;
    // Other relevant attributes

    @OneToMany(mappedBy = "coach")
    private List<Course> courses ;

}