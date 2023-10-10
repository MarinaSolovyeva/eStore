package com.project.estore.controller;

import com.project.estore.entity.User;
import com.project.estore.service.RegistrationService;
import com.project.estore.service.UserService;
//import com.project.estore.util.JWTUtil;
import com.project.estore.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import java.security.Principal;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final UserValidator userValidator;
//    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    public AuthController(RegistrationService registrationService,  UserValidator userValidator, AuthenticationManager authenticationManager, UserService userService) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
//        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

//    @GetMapping ("/token")
//    public String confirmLogin(Principal principal) {
//        User user = userService.findByName(principal.getName());
//        UsernamePasswordAuthenticationToken authInputToken =
//                new UsernamePasswordAuthenticationToken(user.getUsername(),
//                        user.getPassword());
//
//        String token = jwtUtil.generateToken(user.getUsername());
//        return "redirect:/";
//    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user,
                                      BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            return "/auth/registration";

        registrationService.register(user);

        return "redirect:/auth/login";
    }
}
