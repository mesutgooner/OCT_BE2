package com.globits.da.dto;

import java.util.Date;

public class EmployeeCertificateDTO {
    private Long id;
    private CertificateDTO certificate;
    private EmployeeDTO employee;
    private Date validSince;
    private Date validUntil;
    private CityDTO city;

    public EmployeeCertificateDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CertificateDTO getCertificate() {
        return certificate;
    }

    public void setCertificate(CertificateDTO certificate) {
        this.certificate = certificate;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public Date getValidSince() {
        return validSince;
    }

    public void setValidSince(Date validSince) {
        this.validSince = validSince;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }
}
