package com.exam.pavk55.services.service_impl;

import com.exam.pavk55.models.binding.UserLoginBindingModel;
import com.exam.pavk55.models.entities.User;
import com.exam.pavk55.models.service.UserServiceModel;
import com.exam.pavk55.repos.OrderRepo;
import com.exam.pavk55.repos.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final OrderRepo orderRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo,
                           ModelMapper modelMapper,
                           OrderRepo orderRepo) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.orderRepo = orderRepo;
    }

    @Override
    public void addUser(UserServiceModel userServiceModel) {
        userRepo.save(modelMapper.map(userServiceModel, User.class));
    }

    @Override
    public Optional<User> findUserAtLogon(UserLoginBindingModel userLoginBindingModel) {
        return userRepo.findByUsernameAndPassword(userLoginBindingModel.getUsername(),
                userLoginBindingModel.getPassword());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Integer countOrders(User user) {
        return orderRepo.countPerId(user);
    }
}
