package com.globits.da.service.impl;

import com.globits.da.converter.CityConverter;
import com.globits.da.domain.City;
import com.globits.da.dto.CityDTO;
import com.globits.da.repository.CityRepository;
import com.globits.da.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CityConverter cityConverter;

    @Override
    public CityDTO saveOrUpdate(CityDTO dto) {
        if (dto != null) {
            City entity = null;
            if (dto.getId() != null) {
                entity = cityRepository.getCityById(dto.getId());
            }

            entity = cityConverter.toEntity(dto, entity, true);
            if (entity != null)
                entity = cityRepository.save(entity);
            return cityConverter.toDTO(entity, true);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public List<CityDTO> getAll() {
        List<City> entities = cityRepository.findAll();
        if (!entities.isEmpty()) {
            List<CityDTO> dtos = new ArrayList<>();
            entities.forEach(entity -> {
                dtos.add(cityConverter.toDTO(entity,true));
            });
            return dtos;
        }
        return null;
    }
}
