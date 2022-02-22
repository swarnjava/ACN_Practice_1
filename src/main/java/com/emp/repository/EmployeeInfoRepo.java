package com.emp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emp.entity.EmployeeInfo;

@Repository
public interface EmployeeInfoRepo extends JpaRepository<EmployeeInfo, Integer>{

}
