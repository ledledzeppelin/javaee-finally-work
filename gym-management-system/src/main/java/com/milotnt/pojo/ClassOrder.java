package com.milotnt.pojo;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;

/**
 * @author MiloTnT [milotntspace@gmail.com]
 * @date 2021/8/10
 */

@Data
public class ClassOrder {

    private Integer classOrderId;
    private Integer classId;
    private String className;
    private String coach;
    private String memberName;
    private Integer memberAccount;
    private String classBegin;

    public ClassOrder() {
    }

    public ClassOrder(Integer classId, String className, String coach, String memberName, Integer memberAccount, String classBegin) {
        this.classId = classId;
        this.className = className;
        this.coach = coach;
        this.memberName = memberName;
        this.memberAccount = memberAccount;
        this.classBegin = classBegin;
    }



    @Override
    public String toString() {
        return "ClassOrder{" +
                "classOrderId=" + classOrderId +
                ", classId=" + classId +
                ", className='" + className + '\'' +
                ", coach='" + coach + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberAccount=" + memberAccount +
                ", classBegin='" + classBegin + '\'' +
                '}';
    }
}
