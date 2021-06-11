package com.exam.pavk55.models.service;

import com.exam.pavk55.models.entities.Category;
import com.exam.pavk55.models.entities.User;
import com.exam.pavk55.models.enums.CategoryEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderServiceModel {

    private String name;
    private BigDecimal price;
    private LocalDateTime localDateTime;
    private String description;
    private User employeeFk;
    private CategoryEnum categoryEnum;

    public OrderServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getEmployeeFk() {
        return employeeFk;
    }

    public void setEmployeeFk(User employeeFk) {
        this.employeeFk = employeeFk;
    }

    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
    }
}
