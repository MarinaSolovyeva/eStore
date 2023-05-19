package com.example.estote.repositories;

import com.example.estote.entity.Order;
import com.example.estote.entity.User;
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
