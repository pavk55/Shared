package com.exam.pavk55.repos;

import com.exam.pavk55.models.entities.Category;
import com.exam.pavk55.models.entities.Order;
import com.exam.pavk55.models.entities.User;
import com.exam.pavk55.models.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, String> {
    @Query("select sum(o.categoryFk.neededTime) from Order o")
    Integer ReturnOrderTimes();

    @Query("select o from Order o where o.categoryFk = :categoryFk order by o.price desc")
    List<Order> findAllByEnum(Category categoryFk);

    @Query("select count(o.id) from Order o where o.employeeFk = :user")
    Integer countPerId(User user);
}
