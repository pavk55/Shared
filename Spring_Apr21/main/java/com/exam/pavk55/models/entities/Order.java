package com.exam.pavk55.models.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(nullable = false)
    @Size(min = 3, max = 20)
    private String name;

    @Column(nullable = false)
    @DecimalMin("0")
    private BigDecimal price;

    @Column(nullable = false, name = "local_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past")
    private LocalDateTime localDateTime;

    @Column(nullable = false)
    @Size(min = 5)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User employeeFk;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category categoryFk;

    public Order() {
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

    public Category getCategoryFk() {
        return categoryFk;
    }

    public void setCategoryFk(Category categoryFk) {
        this.categoryFk = categoryFk;
    }
}
