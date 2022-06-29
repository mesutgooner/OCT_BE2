package com.globits.da.dto.search;

import com.globits.da.dto.CityDTO;

public class DistrictSearchDTO extends BaseSearchDTO{
    private CityDTO city;

    public DistrictSearchDTO() {
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }
}
