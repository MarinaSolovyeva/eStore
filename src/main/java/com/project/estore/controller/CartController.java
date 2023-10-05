package com.project.estore.controller;

import com.project.estore.entity.Cart;
import com.project.estore.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String aboutCart(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("cart", new Cart());
        } else {
            Cart cart = cartService.getCartByUser(principal.getName());
            model.addAttribute("cart", cart);
        }
        return "static/user-pages/cart";
    }
}



