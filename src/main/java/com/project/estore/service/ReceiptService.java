package com.project.estore.service;

import com.project.estore.entity.Receipt;
import com.project.estore.entityDetail.ReceiptComparator;

import com.project.estore.repositories.ReceiptRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ReceiptService {
    /**
     * Admin have a function to create a receipt: one receipt can contain different articles of goods and
     * different quantity. Admin will see from drop-down list all articles from DB and set the quantity.
     *

     *The quantity will affect the balance of the good, and the User will not be able to add an item to
     *the cart that is out of stock.
     */

    private final ReceiptRepository receiptRepository;
    private final GeneralService generalService;

    public ReceiptService(ReceiptRepository receiptRepository, GeneralService generalService) {
        this.receiptRepository = receiptRepository;
        this.generalService = generalService;
    }

    @Transactional
    public List<Receipt> getAllReceipts() {
        List<Receipt> receipt = receiptRepository.findAll();
        Collections.sort(receipt, new ReceiptComparator());
        Collections.reverse(receipt);
        return receipt;
    }

    @Transactional
    public Receipt getById(Long id) {
        Optional<Receipt> receipt = receiptRepository.findById(id);
        return receipt.orElse(null);
    }

    @Transactional
    public void saveReceipt(Receipt receipt) {
        receipt.setDate(generalService.getCurrentDate());
        receiptRepository.save(receipt);
    }

    @Transactional
    public void deleteReceipt(Long id) {
        receiptRepository.deleteById(id);
    }

    @Transactional
    public void updateReceipt(Long id, Receipt updatedReceipt) {
        updatedReceipt.setId(id);
        receiptRepository.save(updatedReceipt);
    }
}

