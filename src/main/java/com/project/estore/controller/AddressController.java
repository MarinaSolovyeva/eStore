package com.project.estore.controller;

import com.project.estore.entity.Address;
import com.project.estore.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @RequestMapping("/addresses")
    public String showAllAddresses(Model model, Principal principal) {
        List<Address> allAddress = addressService.getAllAddressesByUser(principal.getName());
        model.addAttribute("allAddress", allAddress);
        return "user-pages/profile_folder/addresses";
    }

    @GetMapping ("/addAddress")
    public String addNewAddress(Model model) {
        Address address = new Address();
        model.addAttribute("address", address);
        return "user-pages/profile_folder/addressInfo";}

    @PostMapping("/saveAddress")
    public String saveAddress(@ModelAttribute("address") Address address, Principal principal) {
        addressService.saveAddress(address, principal.getName());
        return "redirect:/profile"; }

    @GetMapping("/updateAddress")
    public String updateAddress(@RequestParam("addressId") long id, Model model) {
        Address address = addressService.getOneById(id);
        model.addAttribute("address", address);
        return "user-pages/profile_folder/addressInfo";}

    @GetMapping("/deleteAddress")
    public String deleteAddress(@RequestParam("addressId") long id) {
        addressService.deleteAddress(id);
        return "redirect:/profile";}
}



