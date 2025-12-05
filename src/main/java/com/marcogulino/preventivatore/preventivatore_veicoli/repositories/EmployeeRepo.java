package com.marcogulino.preventivatore.preventivatore_veicoli.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcogulino.preventivatore.preventivatore_veicoli.models.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByUserName(String userName);
}
