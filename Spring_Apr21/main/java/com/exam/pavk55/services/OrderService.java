package com.exam.pavk55.services;

import com.exam.pavk55.models.entities.Category;
import com.exam.pavk55.models.entities.Order;
import com.exam.pavk55.models.enums.CategoryEnum;
import com.exam.pavk55.models.service.OrderServiceModel;

import java.util.List;

public interface OrderService {
    void addOrder(OrderServiceModel map);

    Integer AllOrderTimes();

    List<Order> getAllOrders(Category categoryFk);

    void removeOrder(String id);
}
