package com.exam.pavk55.services.service_impl;

import com.exam.pavk55.models.entities.Category;
import com.exam.pavk55.models.enums.CategoryEnum;
import com.exam.pavk55.repos.CategoryRepo;
import com.exam.pavk55.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category findByEnum(CategoryEnum categoryEnum) {
        return categoryRepo.FindCategoryByEnum(categoryEnum);
    }
}
