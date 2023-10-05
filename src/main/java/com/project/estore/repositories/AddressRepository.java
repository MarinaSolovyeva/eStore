package com.project.estore.repositories;

import com.project.estore.entity.Address;
import com.project.estore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {


    @Query(value = "SELECT o FROM Address o WHERE o.user = :user")
    List<Address> findAllAddressesByUser(@Param("user") User user) ;

}