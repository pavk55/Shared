package com.exam.pavk55.initializers;

import com.exam.pavk55.models.entities.Category;
import com.exam.pavk55.models.enums.CategoryEnum;
import com.exam.pavk55.repos.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoryInit implements CommandLineRunner {

    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryInit(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        if (categoryRepo.count() == 0) {
            initCategories();
        }
    }

    private void initCategories() {
        for (CategoryEnum enumCategory : CategoryEnum.values()) {
            Category category = new Category();
            category.setCategoryEnum(enumCategory);
            category.setNeededTime(enumCategory.getNeededTime());
            categoryRepo.save(category);
        }
    }
}
