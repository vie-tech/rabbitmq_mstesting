package com.favourcode.customer.repository;

import com.favourcode.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Modifying
    @Query("UPDATE Customer c SET c.status = 'blocked' WHERE c.publicId = :id")
    void blockUserAccount(@Param("id") String id);

    Optional<Customer> findByPublicId(String publicId);
}
