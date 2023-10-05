package com.project.estore.repositories;

import com.project.estore.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository <Receipt, Long> {
}
