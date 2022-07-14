package com.globits.da.service;

import com.globits.da.dto.EmployeeCertificateDTO;

public interface EmployeeCertificateService {
    Object saveOrUpdate(EmployeeCertificateDTO dto);

    Object getAll();
}
