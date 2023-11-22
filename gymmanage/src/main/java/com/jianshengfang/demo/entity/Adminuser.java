package com.jianshengfang.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

/**
 * @Description: 管理员信息实体类
 */
@Entity      //标识一个 Java 类作为 JPA 的实体类，表明该类的对象可以映射到数据库中的表。
@Table(name = "adminusers")
@Data
public class Adminuser {
    @Id                         //标识实体类中作为数据库表主键的属性
    @GeneratedValue(strategy =  GenerationType.AUTO)   //主键的生成策略为自动选择适合数据库的主键生成策略。
    private long adminId;
    private String adminName;
    private String adminPassword;
}