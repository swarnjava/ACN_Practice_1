package com.emp.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emp.customer.entity.CustomerInfo;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerInfo, Integer>{

}
