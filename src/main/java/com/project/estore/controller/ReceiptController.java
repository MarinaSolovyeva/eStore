package com.project.estore.controller;

import com.project.estore.entity.Good;
import com.project.estore.entity.Receipt;

import com.project.estore.service.GoodService;
import com.project.estore.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        List<Good> allGoods = goodService.getAllGoods();
        //TODO: implement of receipt creation
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
    public Map<String, String> addContactWs(@RequestBody Map<String, String> json) {
        //TODO: implement a mapper for receipt

 //     ObjectMapper objectMapper = new ObjectMapper();
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


