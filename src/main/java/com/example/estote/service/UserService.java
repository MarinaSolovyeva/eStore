package com.example.estote.service;

import com.example.estote.entity.User;
import com.example.estote.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void update(long id, User updatedUser) {
        Optional<User> oldUser = userRepository.findById(id);
        updatedUser.setId(id);
        updatedUser.setPassword(oldUser.get().getPassword());
        updatedUser.setUsername(oldUser.get().getUsername());
        updatedUser.setRole(oldUser.get().getRole());
        updatedUser.setCart(oldUser.get().getCart());
        userRepository.save(updatedUser);

    }
    @Transactional
    public User getUser(long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }

    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public User findByName(String username) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        return foundUser.orElse(null);
    }

}
