package com.example.estote.repositories;

import com.example.estote.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository <Receipt, Long> {
}
