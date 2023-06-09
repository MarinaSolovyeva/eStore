package com.example.estote.service;

import com.example.estote.entity.User;
import com.example.estote.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final GeneralService generalService;


    @Autowired
    public RegistrationService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, GeneralService generalService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

        this.generalService = generalService;
    }

    /**
     * register() before saving User to DB, method set encode password to User and set Role by default "USER"
     */
    @Transactional
    public void register(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        user.setRecordingDate(generalService.getCurrentDate());
        userRepository.save(user);
    }
}
