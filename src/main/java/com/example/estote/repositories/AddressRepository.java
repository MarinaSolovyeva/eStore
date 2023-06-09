package com.example.estote.repositories;

import com.example.estote.entity.Address;
import com.example.estote.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    /**
     * FindAllAddressesByUser() finds all addresses by User from DB
     */
    @Query(value = "SELECT o FROM Address o WHERE o.user = :user")
    List<Address> findAllAddressesByUser(@Param("user") User user) ;

}