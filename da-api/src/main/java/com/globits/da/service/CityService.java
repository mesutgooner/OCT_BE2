package com.globits.da.service;

import com.globits.da.dto.CityDTO;

import java.util.List;

public interface CityService {
    CityDTO saveOrUpdate(CityDTO dto);

    void delete(Long id);

    List<CityDTO> getAll();
}
