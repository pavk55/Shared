package com.exam.pavk55.models.enums;

public enum CategoryEnum {

    COFFEE(2),
    CAKE(10),
    DRINK(1),
    OTHER(5);

    private Integer neededTime;

    CategoryEnum(Integer neededTime) {
        this.neededTime = neededTime;
    }

    public Integer getNeededTime() {
        return neededTime;
    }

    public void setNeededTime(Integer neededTime) {
        this.neededTime = neededTime;
    }
}
