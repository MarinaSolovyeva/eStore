package com.example.estote.repositories;

import com.example.estote.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodRepository extends JpaRepository <Good, Long> {

}
