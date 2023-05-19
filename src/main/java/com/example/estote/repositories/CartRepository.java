package com.example.estote.repositories;


import com.example.estote.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository <Cart, Long> {


}
