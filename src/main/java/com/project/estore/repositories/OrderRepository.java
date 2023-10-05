package com.project.estore.repositories;

import com.project.estore.entity.Order;
import com.project.estore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long> {

    @Query(value = "SELECT o FROM Order o WHERE o.userId = :user")
    List<Order> findAllOrdersByUserId(@Param("user") User user);
}
