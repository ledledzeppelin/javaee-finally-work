package com.milotnt.pojo;

import lombok.Data;

/**
 * @author MiloTnT [milotntspace@gmail.com]
 * @date 2021/8/10
 */
@Data
public class Employee {

    private Integer employeeAccount;
    private String employeeName;
    private String employeeGender;
    private Integer employeeAge;
    private String entryTime;
    private String staff;
    private String employeeMessage;

    public Employee() {
    }

    public Employee(Integer employeeAccount, String employeeName, String employeeGender, Integer employeeAge, String entryTime, String staff, String employeeMessage) {
        this.employeeAccount = employeeAccount;
        this.employeeName = employeeName;
        this.employeeGender = employeeGender;
        this.employeeAge = employeeAge;
        this.entryTime = entryTime;
        this.staff = staff;
        this.employeeMessage = employeeMessage;
    }




    @Override
    public String toString() {
        return "Employee{" +
                ", employeeAccount=" + employeeAccount +
                ", employeeName='" + employeeName + '\'' +
                ", employeeGender='" + employeeGender + '\'' +
                ", employeeAge=" + employeeAge +
                ", entryTime='" + entryTime + '\'' +
                ", staff='" + staff + '\'' +
                ", employeeMessage='" + employeeMessage + '\'' +
                '}';
    }
}
