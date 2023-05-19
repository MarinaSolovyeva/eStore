package com.example.estote.controller;

import com.example.estote.entity.Good;
import com.example.estote.entity.Receipt;
import com.example.estote.service.GoodService;
import com.example.estote.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
        Receipt receipt = receiptService.createNewReceipt();
        model.addAttribute("receipt", receipt);
        Map <Long, Integer> goodsAmount = receipt.getGoodsAmount();
        model.addAttribute("goodsAmount", goodsAmount);
        return "admin/receiptInfo";
    }

    //        List<Good> goodsForReceipt = receipt.getGoods();
//        model.addAttribute("goodsForReceipt", goodsForReceipt);

    @PostMapping("/saveReceipt")
    public String saveReceipt(@ModelAttribute("receipt") Receipt receipt) {
        receiptService.saveReceipt(receipt);
        return "redirect:/admin/receipt";
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


