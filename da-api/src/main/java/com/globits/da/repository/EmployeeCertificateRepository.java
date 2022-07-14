package com.globits.da.repository;

import com.globits.da.domain.Certificate;
import com.globits.da.domain.City;
import com.globits.da.domain.Employee;
import com.globits.da.domain.EmployeeCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface EmployeeCertificateRepository extends JpaRepository<EmployeeCertificate,Long> {
    EmployeeCertificate getById(Long id);
    List<EmployeeCertificate> getByEmployee(Employee employee);
    int countByCertificateAndAndCityAndEmployee(Certificate certificate, City city,Employee employee);

}
