package com.globits.da.service.impl;

import com.globits.da.converter.DistrictConverter;
import com.globits.da.domain.City;
import com.globits.da.domain.District;
import com.globits.da.dto.CityDTO;
import com.globits.da.dto.DistrictDTO;
import com.globits.da.repository.CityRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DistrictConverter districtConverter;

    @Override
    public DistrictDTO saveOrUpdate(DistrictDTO dto) {
        if (dto != null) {
            District entity = null;
            if (dto.getId() != null) {
                entity = districtRepository.getDistrictById(dto.getId());
            }
            entity = districtConverter.toEntity(dto, entity, true, true);
            if (entity != null)
                entity = districtRepository.save(entity);
            return districtConverter.toDTO(entity,true,true);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        districtRepository.deleteById(id);
    }

    @Override
    public List<DistrictDTO> getByCity(Long id) {
        City city = cityRepository.getCityById(id);
        if (city != null) {
            if (!city.getDistricts().isEmpty()) {
                List<DistrictDTO> dtos = new ArrayList<>();
                city.getDistricts().forEach(entity -> {
                    DistrictDTO dto = new DistrictDTO();
                    dto.setId(entity.getId());
                    dto.setCode(entity.getCode());
                    dto.setName(entity.getName());
                    CityDTO cityDTO = new CityDTO();
                    cityDTO.setId(city.getId());
                    cityDTO.setCode(city.getCode());
                    cityDTO.setName(city.getName());
                    dto.setCity(cityDTO);
                    dtos.add(dto);
                });
                return dtos;
            }
        }
        return null;
    }

    @Override
    public List<DistrictDTO> getAll() {
        List<District> entities = districtRepository.findAll();
        if (!entities.isEmpty()) {
            List<DistrictDTO> dtos = new ArrayList<>();
            entities.forEach(entity -> {
                dtos.add(districtConverter.toDTO(entity,true,true));
            });
            return dtos;
        }
        return null;
    }
}
