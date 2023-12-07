package com.milotnt.pojo;

import lombok.Data;

/**
 * @author MiloTnT [milotntspace@gmail.com]
 * @date 2021/8/10
 */
@Data
public class ClassTable {

    private Integer classId;
    private String className;
    private String classBegin;
    private String classTime;
    private String coach;





    @Override
    public String toString() {
        return "ClassTable{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", classBegin='" + classBegin + '\'' +
                ", classTime='" + classTime + '\'' +
                ", coach='" + coach + '\'' +
                '}';
    }
}
