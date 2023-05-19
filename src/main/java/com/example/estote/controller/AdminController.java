package com.example.estote.controller;

import com.example.estote.entity.Good;
import com.example.estote.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping  ("/admin")
public class AdminController {

    @Autowired
    private GoodService goodService;

    @GetMapping  ("")
    public String showGeneralPage() {
        return "admin/admin-general";}

    @GetMapping  ("/allGoods")
    public String showAllGoods(Model model) {
        List<Good> allGoods = goodService.getAllGoods();
        model.addAttribute("allGoods", allGoods);
        return "admin/admin";}

    @GetMapping ("/addNewGood")
    public String addNewGood(Model model) {
        Good good = new Good();
        model.addAttribute("good", good);
        return "admin/adminInfo";}

    @PostMapping ("/saveGood")
    public String saveGood(@ModelAttribute("good") Good good) {
        goodService.saveGood(good);
        return "redirect:/admin"; }

    @GetMapping("/updateInfo")
    public String updateGood(@RequestParam("goodId") long id, Model model) {
        Good good = goodService.getGood(id);
        model.addAttribute("good", good);
        return "admin/adminInfo";}

    @GetMapping("/deleteGood")
    public String deleteGood(@RequestParam("goodId") long id) {
        goodService.deleteGood(id);
        return "redirect:/admin";}


}


