package com.example.estote.service;

import com.example.estote.entity.Address;
import com.example.estote.entity.User;
import com.example.estote.repositories.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserService userService;


    public AddressService(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }
    @Transactional
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Transactional
    public List<Address> getAllAddressesByUser(String name) {
        User user = userService.findByName(name);
        List <Address> addressList = addressRepository.findAllAddressesByUser(user);
        return addressList;
    }
    @Transactional
    public void saveAddress(Address address, String name) {
        User user = userService.findByName(name);
        address.setUser(user);
        addressRepository.save(address);
    }

    @Transactional
    public Address getOneById (long id) {
        Optional <Address> address = addressRepository.findById(id);
        return address.orElse(null);
    }

    @Transactional
    public void update(long id, Address updatedAddress) {
        updatedAddress.setId(id);
        addressRepository.save(updatedAddress);}

    @Transactional
    public void deleteAddress(long id) {
        addressRepository.deleteById(id);
    }
}
