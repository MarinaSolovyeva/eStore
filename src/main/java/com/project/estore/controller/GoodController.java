package com.project.estore.controller;

import com.project.estore.entity.Good;
import com.project.estore.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;
import java.util.List;

@Controller
public class GoodController {

    @Autowired
    private GoodService goodService;

    @GetMapping("/catalog")
    public String showCatalog(Model model) {
        List<Good> allGoods = goodService.getAllGoods();
        model.addAttribute("allGoods", allGoods);
        return "user-pages/catalog";
    }

    @GetMapping("/{id}/cart")
    public String addCart(@PathVariable Long id, Principal principal) {
        if (principal == null) {
            return "redirect:/user-pages/catalog";
        }
        goodService.addToUserCart(id, principal.getName());
        return "redirect:/catalog";
    }

    @GetMapping("/{id}/cart/delete")
    public String deleteGoodFromCart(@RequestParam("detailGoodId") long id, Principal principal) {
        Good good = goodService.getGood(id);
        if (principal == null) {
            return "redirect:/user-pages/catalog";
        }
        goodService.deleteFromUserCart(good, principal.getName());
        return "redirect:/cart";
    }

}




