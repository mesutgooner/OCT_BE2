package com.globits.da.repository;

import com.globits.da.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findAllByCode(String code);
    Employee getEmployeeById(Long id);
}
