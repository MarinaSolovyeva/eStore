package com.project.estore.repositories;

import com.project.estore.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodRepository extends JpaRepository <Good, Long> {

}
