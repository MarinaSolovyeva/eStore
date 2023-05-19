package com.example.estote.service;

import com.example.estote.entity.Good;
import com.example.estote.entity.Receipt;

import com.example.estote.entityDetail.ReceiptComporator;
import com.example.estote.repositories.ReceiptRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final GeneralService generalService;
    private final GoodService goodService;

    public ReceiptService(ReceiptRepository receiptRepository, GeneralService generalService, GoodService goodService) {
        this.receiptRepository = receiptRepository;
        this.generalService = generalService;
        this.goodService = goodService;
    }


    @Transactional
    public List <Receipt> getAllReceipts () {
        List <Receipt> receipt = receiptRepository.findAll();
        Collections.sort(receipt, new ReceiptComporator());
        Collections.reverse(receipt);
        return receipt;
    }

    @Transactional
    public Receipt getById (Long id) {
        Optional <Receipt> receipt = receiptRepository.findById(id);
        return receipt.orElse(null);
    }
    @Transactional
    public void saveReceipt (Receipt receipt) {
        receipt.setDate(generalService.getCurrentDate());
        receiptRepository.save(receipt);
    }

    @Transactional
    public void deleteReceipt (Long id) {
        receiptRepository.deleteById(id);
    }

    @Transactional
    public void updateReceipt (Long id, Receipt updatedReceipt) {
        updatedReceipt.setId(id);
        receiptRepository.save(updatedReceipt);
    }


    //aggregate info for order view
    public Receipt createNewReceipt() {
        Receipt receipt = new Receipt();
        Map<Long, Integer> goodsAmount= new HashMap<>();


        List<Good> goodsReceipt = goodService.getAllGoods();
        for (Good good : goodsReceipt) {
            goodsAmount.put(good.getId(), null);}

        receipt.setGoods(goodsReceipt);
        receipt.setGoodsAmount(goodsAmount);
        return receipt;

//        Map<Long, ReceiptDetail> mapByGoodId = new HashMap<>();
//        detail = goodsAmount.get(good.getId());
//            if (detail == null) {
//                mapByGoodId.put(good.getId(), new ReceiptDetail(good));
//            } else {
//                detail.setAmount(detail.getAmount().add(new BigDecimal(1.0)));
//            }
//        }

////        receipt.getAmountList().add(detail.getAmount());
//        receipt.setReceiptDetails(new ArrayList<>(mapByGoodId.values()));
//        receipt.setGoods(goodsReceipt);
////        receipt.aggregate();
//        return receipt;
    }
}
