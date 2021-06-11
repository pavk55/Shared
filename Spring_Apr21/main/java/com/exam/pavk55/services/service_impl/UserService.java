package com.exam.pavk55.services.service_impl;

import com.exam.pavk55.models.binding.UserLoginBindingModel;
import com.exam.pavk55.models.entities.User;
import com.exam.pavk55.models.service.UserServiceModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(UserServiceModel map);

    Optional<User> findUserAtLogon(UserLoginBindingModel userLoginBindingModel);

    List<User> getAllUsers();

    Integer countOrders(User user);
}
