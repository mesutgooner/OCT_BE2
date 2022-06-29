package com.globits.da.dto;

import java.util.ArrayList;
import java.util.List;


public class DistrictDTO {
    private Long id;
    private String name;
    private String code;
    private CityDTO city;
    private List<WardDTO> wards = new ArrayList<>();

    public DistrictDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

    public List<WardDTO> getWards() {
        return wards;
    }

    public void setWards(List<WardDTO> wards) {
        this.wards = wards;
    }
}
