package com.exam.pavk55.repos;

import com.exam.pavk55.models.entities.Category;
import com.exam.pavk55.models.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, String> {

    @Query("select c from Category c where c.categoryEnum = :categoryEnum")
    Category FindCategoryByEnum(CategoryEnum categoryEnum);
}
