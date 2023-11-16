package com.liujian.gymxmjpa.dao;


import com.liujian.gymxmjpa.entity.Adminuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * @Description: 管理员Dao层接口
 */
@Transactional   //声明为事物
//通过继承 JpaRepository，该接口将继承 Spring Data JPA 提供的一些默认方法，例如常见的 CRUD 操作（增加、删除、修改、查询）
public interface AdminuserDao extends JpaRepository<Adminuser,Long> {
    Adminuser findByAdminNameAndAdminPassword(String name,String password);


    @Modifying    //记执行修改操作的方法
    //指定自定义的更新 SQL 语句
    @Query(value = "update  adminuser set adminPassword =:adminPassword where adminId =:adminId",nativeQuery = true)
    void updPassword(@Param("adminId") long adminId,@Param("adminPassword") String adminPassword) ;
     //@Param 注解将方法参数 adminId 和 adminPassword 与 SQL 语句中的 :adminId 和 :adminPassword 绑定


}
