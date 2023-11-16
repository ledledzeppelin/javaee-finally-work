package com.jianshengfang.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity      //标识一个 Java 类作为 JPA 的实体类，表明该类的对象可以映射到数据库中的表。
@Table(name = "users")
@Data
public class User {
    @Id                         //标识实体类中作为数据库表主键的属性
    @GeneratedValue(strategy = GenerationType.AUTO)   //主键的生成策略为自动选择适合数据库的主键生成策略。

    private int userId;
    private String name;
    private String account;
    private String password;
    private String phoneNumber;
    // Other relevant attributes
    @OneToMany(mappedBy = "user")    //指定一对多的关系
    private List<Log> logs;


    @ManyToMany              //指定多对多的关系
    @JoinTable(
            name = "allcourses",            //指定了关联表的名称为 courses
            joinColumns = @JoinColumn(name = "user_id"),        //当前实体（User）在中间表中的外键列名为 user_id
            inverseJoinColumns = @JoinColumn(name = "user_course_id")   //另一个实体（Course）在中间表中的外键列名为 course_id
    )
    private List<Course> courses;

    @OneToMany(mappedBy = "user")
    private List<Equipment> borrowedEquipment;
}