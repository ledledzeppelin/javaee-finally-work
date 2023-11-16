package com.jianshengfang.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity      //标识一个 Java 类作为 JPA 的实体类，表明该类的对象可以映射到数据库中的表。
@Table(name = "allcourses")
@Data
public class Course {
    @Id                         //标识实体类中作为数据库表主键的属性
    @GeneratedValue(strategy = GenerationType.AUTO)   //主键的生成策略为自动选择适合数据库的主键生成策略。

    private int courseId;
    private String courseName;
    private String description;
    private String schedule;
    // Other relevant attributes

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @ManyToMany(mappedBy = "courses")
    private List<User> users;

}