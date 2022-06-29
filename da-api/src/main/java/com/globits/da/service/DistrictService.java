package com.globits.da.service;

import com.globits.da.dto.DistrictDTO;

import java.util.List;

public interface DistrictService {
    DistrictDTO saveOrUpdate(DistrictDTO dto);
    void delete(Long id);

    List<DistrictDTO> getByCity(Long id);

    List<DistrictDTO> getAll();
}
