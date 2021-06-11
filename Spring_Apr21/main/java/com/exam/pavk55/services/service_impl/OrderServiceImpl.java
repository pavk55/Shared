package com.exam.pavk55.services.service_impl;

import com.exam.pavk55.models.entities.Category;
import com.exam.pavk55.models.entities.Order;
import com.exam.pavk55.models.entities.User;
import com.exam.pavk55.models.enums.CategoryEnum;
import com.exam.pavk55.models.service.OrderServiceModel;
import com.exam.pavk55.repos.CategoryRepo;
import com.exam.pavk55.repos.OrderRepo;
import com.exam.pavk55.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;
    private final CategoryRepo categoryRepo;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo,
                            ModelMapper modelMapper,
                            HttpSession httpSession,
                            CategoryRepo categoryRepo) {
        this.orderRepo = orderRepo;
        this.modelMapper = modelMapper;
        this.httpSession = httpSession;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void addOrder(OrderServiceModel orderServiceModel) {
//        add user
        User userToAdd = (User) httpSession.getAttribute("user");
        orderServiceModel.setEmployeeFk(userToAdd);

//        addCategory
        Category categoryToAdd = categoryRepo.FindCategoryByEnum(orderServiceModel.getCategoryEnum());
        Order order = modelMapper.map(orderServiceModel, Order.class);
        order.setCategoryFk(categoryToAdd);

//        save to repo
        orderRepo.save(order);
    }

    @Override
    public Integer AllOrderTimes() {
        return orderRepo.ReturnOrderTimes();
    }

    @Override
    public List<Order> getAllOrders(Category categoryFk) {
        return orderRepo.findAllByEnum(categoryFk);
    }

    @Override
    public void removeOrder(String id) {
        orderRepo.deleteById(id);
    }
}
