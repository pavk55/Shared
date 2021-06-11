package com.exam.pavk55.services;

import com.exam.pavk55.models.entities.Category;
import com.exam.pavk55.models.enums.CategoryEnum;

public interface CategoryService {
    Category findByEnum(CategoryEnum cake);
}
