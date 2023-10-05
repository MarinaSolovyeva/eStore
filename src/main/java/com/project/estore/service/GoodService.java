package com.project.estore.service;

import com.project.estore.entity.Cart;
import com.project.estore.entity.Good;
import com.project.estore.entity.User;
import com.project.estore.repositories.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GoodService {

    private final GoodRepository goodRepository;
    private final UserService userService;
    private final CartService cartService;


    @Autowired
    public GoodService(GoodRepository goodRepository, UserService userService, CartService cartService) {
        this.goodRepository = goodRepository;
        this.userService = userService;
        this.cartService = cartService;
    }

    @Transactional
    public List<Good> getAllGoods() {
        return goodRepository.findAll();
    }

    @Transactional
    public void saveGood(Good good) {
        goodRepository.save(good);
    }

    @Transactional
    public void update(long id, Good updatedGood) {
        updatedGood.setId(id);
        goodRepository.save(updatedGood);
    }

    @Transactional
    public Good getGood(long id) {
        Optional<Good> foundGood = goodRepository.findById(id);
        return foundGood.orElse(null);
    }

    @Transactional
    public void deleteGood(long id) {
        goodRepository.deleteById(id);
    }

    @Transactional
    public void addToUserCart(Long goodId, String username) {
        User user = userService.findByName(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        Cart cart = user.getCart();
        if (cart == null) {
            Cart newCart = cartService.createCart(user, Collections.singletonList(goodId));
            user.setCart(newCart);
        } else {
            cartService.addGood(cart, Collections.singletonList(goodId));
        }
    }

    @Transactional
    public void deleteFromUserCart(Good good, String username) {
        User user = userService.findByName(username);
        Cart cart = user.getCart();
        cartService.deleteGood(cart, good);
    }

}


