package com.globits.da.dto;

import com.globits.da.domain.City;

import java.util.Date;

public class CertificateDTO {
    private Long id;
    private String code;
    private String name;
    private CityDTO city;

    public CertificateDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }
}
