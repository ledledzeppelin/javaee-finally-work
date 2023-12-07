package com.milotnt.pojo;

import lombok.Data;

/**
 * @author MiloTnT [milotntspace@gmail.com]
 * @date 2021/8/10
 */

@Data
public class Equipment {

    private Integer equipmentId;
    private String equipmentName;
    private String equipmentLocation;
    private String equipmentStatus;
    private String equipmentMessage;

    public Equipment() {
    }

    public Equipment(Integer equipmentId, String equipmentName, String equipmentLocation, String equipmentStatus, String equipmentMessage) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.equipmentLocation = equipmentLocation;
        this.equipmentStatus = equipmentStatus;
        this.equipmentMessage = equipmentMessage;
    }



    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId=" + equipmentId +
                ", equipmentName='" + equipmentName + '\'' +
                ", equipmentLocation='" + equipmentLocation + '\'' +
                ", equipmentStatus='" + equipmentStatus + '\'' +
                ", equipmentMessage='" + equipmentMessage + '\'' +
                '}';
    }
}
