package com.globits.da.service.impl;

import com.globits.da.common.Error;
import com.globits.da.converter.EmployeeCertificateConverter;
import com.globits.da.converter.EmployeeConverter;
import com.globits.da.domain.EmployeeCertificate;
import com.globits.da.dto.EmployeeCertificateDTO;
import com.globits.da.dto.ResponseDTO;
import com.globits.da.repository.EmployeeCertificateRepository;
import com.globits.da.service.EmployeeCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;

@Service
public class EmployeeCertificateServiceImpl implements EmployeeCertificateService {
    @Autowired
    private EmployeeCertificateRepository employeeCertificateRepository;
    @Autowired
    private EmployeeCertificateConverter employeeCertificateConverter;
    @Autowired
    private EmployeeConverter employeeConverter;

    @Override
    public Object saveOrUpdate(EmployeeCertificateDTO dto) {
        if (dto != null) {
            List<ResponseDTO> errors = new ArrayList<>();
            EmployeeCertificate entity = null;
            if (dto.getId() != null) {
                entity = employeeCertificateRepository.getById(dto.getId());
            }
            entity = employeeCertificateConverter.toEntity(dto, entity);
            if (entity != null) {
                errors = validateEmployeeCert(entity);
                if (errors.isEmpty())
                    entity = employeeCertificateRepository.save(entity);
                else return errors;
                return employeeCertificateConverter.toDTO(entity);
            }
        }

        return null;
    }

    @Override
    public Object getAll() {
        List<EmployeeCertificate> employeeCertificates = employeeCertificateRepository.findAll();
        List<EmployeeCertificateDTO> employeeCertificateDTOS = new ArrayList<>();
        for (EmployeeCertificate employeeCertificate : employeeCertificates) {
            employeeCertificateDTOS.add(employeeCertificateConverter.toDTO(employeeCertificate));
        }
        return employeeCertificateDTOS;
    }

    private List<ResponseDTO> validateEmployeeCert(EmployeeCertificate entity) {
        List<ResponseDTO> errors = new ArrayList<>();
        //check duplicate and valid
        int countExistedCert = employeeCertificateRepository.countByCertificateAndAndCityAndEmployee(entity.getCertificate(), entity.getCity(), entity.getEmployee());

        Instant nowUtc = Instant.now();
        ZoneId asiaHanoi = ZoneId.ofOffset("GMT", ZoneOffset.of("+07"));
        ZonedDateTime nowAsiaHanoi = ZonedDateTime.ofInstant(nowUtc, asiaHanoi);
        Date now = Date.from(nowAsiaHanoi.toInstant());

        if (entity.getValidUntil().after(now) && countExistedCert > 0) {
            errors.add(new ResponseDTO(employeeConverter.toDTO(entity.getEmployee()), Error.CERTIFICATE_IS_EFFECTIVE.getCode(), Error.CERTIFICATE_IS_EFFECTIVE.getMessage()));
        }


        //check over 3 valid certs
        List<EmployeeCertificate> employeeCertificates = employeeCertificateRepository.getByEmployee(entity.getEmployee());
        if (employeeCertificates.size() >= 3) {
            int count = 0;
            for (EmployeeCertificate employeeCertificate : employeeCertificates) {
                if (employeeCertificate.getValidUntil().after(now))
                    count++;
            }
            if (count >= 3)
                errors.add(new ResponseDTO(employeeConverter.toDTO(entity.getEmployee()), Error.CERTIFICATE_NUMBER_EXCEED.getCode(), Error.CERTIFICATE_NUMBER_EXCEED.getMessage()));
        }
        return errors;
    }
}
