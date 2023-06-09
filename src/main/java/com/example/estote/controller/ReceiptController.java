package com.example.estote.controller;

import com.example.estote.entity.Good;
import com.example.estote.entity.Receipt;

import com.example.estote.entityDetail.ReceiptFormModel;
import com.example.estote.entityDetail.ReceiptFormModelWrapper;
import com.example.estote.service.GoodService;
import com.example.estote.service.ReceiptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/receipt")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private GoodService goodService;

    @GetMapping("")
    public String showAllReceipts(Model model) {
        List<Receipt> allReceipts = receiptService.getAllReceipts();
        model.addAttribute("allReceipts", allReceipts);
        return "admin/receipt";
    }

    @GetMapping("/addNewReceipt")
    public String addNewReceipt(Model model) {

        Receipt receipt = new Receipt();
        List <Good> allGoods = goodService.getAllGoods();
//        for (Good good : allGoods) {
//            ReceiptDetail detailForGood = new ReceiptDetail(good);
//            receipt.getReceiptDetails().add(detailForGood);
//            model.addAttribute("receipt", receipt);
//        }
        model.addAttribute("allGoods", allGoods);
        return "admin/receiptInfo";
    }

    @ResponseBody
    @PostMapping(value = "/saveReceipt", consumes = "application/x-www-form-urlencoded")
    public Map<String, String> addContactWs(@RequestBody Map <String, String> json) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String[] selectedItems = objectMapper.readValue(jsonData, String[].class);

//        for (Good good : goodForReceipt) {
//            ReceiptDetail detail = new ReceiptDetail(good);
//            receipt.getReceiptDetails().add(detail);
//        }
//
//        receiptService.saveReceipt(goodsForReceiptreceipt);
//        return "redirect:/admin/receipt";
        return new HashMap<>();
    }

    @GetMapping("/updateInfo")
    public String updateReceipt(@RequestParam("receiptId") long id, Model model) {
        Receipt receipt = receiptService.getById(id);
        model.addAttribute("receipt", receipt);
        return "admin/receiptInfo";
    }

    @GetMapping("/deleteReceipt")
    public String deleteReceipt(@RequestParam("receiptId") long id) {
        receiptService.deleteReceipt(id);
        return "redirect:/admin/receipt";
    }


}


