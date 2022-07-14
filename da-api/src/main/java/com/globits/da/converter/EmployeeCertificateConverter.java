package com.globits.da.converter;

import com.globits.da.domain.Certificate;
import com.globits.da.domain.City;
import com.globits.da.domain.Employee;
import com.globits.da.domain.EmployeeCertificate;
import com.globits.da.dto.EmployeeCertificateDTO;
import com.globits.da.repository.CertificateRepository;
import com.globits.da.repository.CityRepository;
import com.globits.da.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeCertificateConverter {
    @Autowired
    private CertificateConverter certificateConverter;
    @Autowired
    private EmployeeConverter employeeConverter;
    @Autowired
    private CityConverter cityConverter;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CertificateRepository certificateRepository;
    @Autowired
    private CityRepository cityRepository;

    public EmployeeCertificate toEntity(EmployeeCertificateDTO dto, EmployeeCertificate oldEntity){
        if (dto!=null){
            EmployeeCertificate entity = new EmployeeCertificate();
            if (oldEntity!=null)
                entity = oldEntity;
            if (dto.getCertificate()!=null){
                Certificate certificate = null;
                if (dto.getCertificate().getId()!=null){
                    certificate = certificateRepository.getCertificateById(dto.getCertificate().getId());
                }
                certificate = certificateConverter.toEntity(dto.getCertificate(),certificate);
                entity.setCertificate(certificate);
            }
            if (dto.getEmployee()!=null){
                Employee employee = null;
                if (dto.getEmployee().getId()!=null){
                    employee = employeeRepository.getEmployeeById(dto.getEmployee().getId());
                }
                employee = employeeConverter.toEntity(dto.getEmployee(),employee);
                entity.setEmployee(employee);
            }
            if (dto.getCity()!=null){
                City city = null;
                if (dto.getCity().getId()!=null){
                    city = cityRepository.getCityById(dto.getCity().getId());
                }
                city = cityConverter.toEntity(dto.getCity(),city,false);
                entity.setCity(city);
            }
            if (dto.getValidSince()!=null)
                entity.setValidSince(dto.getValidSince());
            if (dto.getValidUntil()!=null)
                entity.setValidUntil(dto.getValidUntil());
            return entity;
        }
        return null;
    }

    public EmployeeCertificateDTO toDTO(EmployeeCertificate entity){
        if (entity!=null){
            EmployeeCertificateDTO dto = new EmployeeCertificateDTO();
            dto.setId(entity.getId());
            dto.setValidSince(entity.getValidSince());
            dto.setValidUntil(entity.getValidUntil());
            dto.setCertificate(certificateConverter.toDTO(entity.getCertificate()));
            dto.setEmployee(employeeConverter.toDTO(entity.getEmployee()));
            dto.setCity(cityConverter.toDTO(entity.getCity(),true));
            return dto;
        }
        return null;
    }
}
