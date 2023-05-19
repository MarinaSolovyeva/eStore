package com.example.estote.controller;

import com.example.estote.entity.*;
import com.example.estote.service.AddressService;
import com.example.estote.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final AddressService addressService;

    public OrderController(OrderService orderService, AddressService addressService) {
        this.orderService = orderService;
        this.addressService = addressService;
    }


    @GetMapping("/new/order")
    public String aboutOrder(Model model, Principal principal) {

        Order order = orderService.getOrderByUser(principal.getName());
        model.addAttribute("order", order);

        List<Address> addresses = addressService.getAllAddressesByUser(principal.getName());
        model.addAttribute("addresses", addresses);

        return "user-pages/order";
    }

    @PostMapping("/saveOrder")
    public String saveOrder(Principal principal,
                            @ModelAttribute("order") Order oldOrder,
                            @ModelAttribute("addresses") Address address,
                            RedirectAttributes redirectAttributes) {

        Order newOrder = orderService.getOrderByUser(principal.getName());
        orderService.copyAllDetailsToOrder(newOrder, oldOrder, address);
        orderService.addToUserOrder(principal.getName(), newOrder);

        redirectAttributes.addAttribute("orderId", newOrder.getId());
        return "redirect:/confirmOrder";
    }

    @GetMapping("/confirmOrder")
    public String confirmOrder(@RequestParam("orderId") Long id, Model model) {
        Order order = orderService.getOrder(id);
        model.addAttribute("order", order);
        return "user-pages/confirm-order";
    }

    @GetMapping("/orders")
    public String showAllOrders(Model model, Principal principal) {
        List<Order> allOrders = orderService.getAllOrdersByUser(principal.getName());
        model.addAttribute("allOrders", allOrders);
        return "user-pages/profile_folder/order_list";
    }
}
