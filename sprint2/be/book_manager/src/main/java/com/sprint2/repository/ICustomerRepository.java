package com.sprint2.repository;

import com.sprint2.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, String> {


}