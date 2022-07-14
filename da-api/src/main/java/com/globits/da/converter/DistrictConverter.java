package com.globits.da.converter;

import com.globits.da.domain.City;
import com.globits.da.domain.District;
import com.globits.da.domain.Ward;
import com.globits.da.dto.DistrictDTO;
import com.globits.da.dto.WardDTO;
import com.globits.da.repository.CityRepository;
import com.globits.da.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DistrictConverter {
    @Autowired
    private CityConverter cityConverter;
    @Autowired
    private WardConverter wardConverter;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private WardRepository wardRepository;

    public District toEntity(DistrictDTO dto, District oldEntity, boolean needCity, boolean needWards) {
        if (dto != null) {
            District entity = new District();
            if (oldEntity != null) {
                entity = oldEntity;
            }
            if (dto.getCode() != null)
                entity.setCode(dto.getCode());
            if (dto.getName() != null)
                entity.setName(dto.getName());

            if (needCity) {
                if (dto.getCity() != null) {
                    City city = null;
                    if (dto.getCity().getId() != null) {
                        city = cityRepository.getCityById(dto.getId());
                    }
                    entity.setCity(cityConverter.toEntity(dto.getCity(), city, false));
                }
            }

            if (needWards) {
                if (!dto.getWards().isEmpty()) {
                    List<Ward> wards = new ArrayList<>();

                    for (WardDTO wardDTO : dto.getWards()) {
                        Ward ward = null;
                        if (wardDTO.getId() != null) {
                            ward = wardRepository.getWardById(wardDTO.getId());
                        }
                        ward = wardConverter.toEntity(wardDTO, ward, true);

                        if (wardDTO.getDistrict() == null) {
                            ward.setDistrict(entity);
                        } //để đảm bảo ward này luôn là con của district
                        wards.add(ward);
                    }
                    entity.setWards(wards);
                }
            }
            return entity;
        }
        return null;
    }


    public DistrictDTO toDTO(District entity, boolean needCity, boolean needWards) {
        if (entity != null) {
            DistrictDTO dto = new DistrictDTO();
            dto.setId(entity.getId());
            dto.setCode(entity.getCode());
            dto.setName(entity.getName());
            City city = entity.getCity();
            if (needCity && city != null) {
                dto.setCity(cityConverter.toDTO(city, false));
            }
            if (needWards) {
                if (!entity.getWards().isEmpty()) {
                    for (Ward ward : entity.getWards()) {
                        dto.getWards().add(wardConverter.toDTO(ward, false));
                    }
                }
            }
            return dto;
        }
        return null;
    }
}
