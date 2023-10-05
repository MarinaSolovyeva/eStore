package com.project.estore.controller;

import com.project.estore.entity.User;
import com.project.estore.security.UserDetail;
import com.project.estore.service.UserDetailService;
import com.project.estore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailService userDetailService;

    @RequestMapping("/")
    public String showMainPage() {
        return "main-page";
    }

    @GetMapping("/user")
    public String showUserInfo(Model model, Principal principal) {
        UserDetail userDetail = (UserDetail) userDetailService.loadUserByUsername(principal.getName());
        User user = userService.getUser(userDetail.getId());
        model.addAttribute("user", user);
        return "static/user-pages/profile_folder/user";
    }

    @PostMapping("/updateUserInfo")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user.getId(), user);
        return "redirect:/user";
    }

    @GetMapping("/profile")
    public String showProfile() {
        return "static/user-pages/profile_folder/profile";
    }
}



