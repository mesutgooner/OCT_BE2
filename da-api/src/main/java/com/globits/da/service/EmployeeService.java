package com.globits.da.service;

import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();

    Object saveOrUpdate(EmployeeDTO dto);

    void delete(Long id);

    Object importFromExcel(MultipartFile file);
}
