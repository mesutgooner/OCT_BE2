package com.globits.da.converter;

import com.globits.da.domain.Certificate;
import com.globits.da.domain.City;
import com.globits.da.dto.CertificateDTO;
import com.globits.da.dto.CityDTO;
import com.globits.da.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CertificateConverter {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CityConverter cityConverter;

    public Certificate toEntity(CertificateDTO dto, Certificate oldEntity){
        if (dto!=null){
            Certificate entity = new Certificate();
            if (oldEntity!=null)
                entity = oldEntity;
            entity.setCode(dto.getCode());
            entity.setName(dto.getName());
            entity.setValidSince(dto.getValidSince());
            entity.setValidUntil(dto.getValidUntil());
            if (dto.getCity()!=null){
                City city = null;
                if (dto.getCity().getId()!=null){
                    city = cityRepository.getCityById(dto.getCity().getId());
                }
                city = cityConverter.toEntity(dto.getCity(),city,false);
                entity.setCity(city);
            }
            return entity;
        }
        return null;
    }

    public CertificateDTO toDTO(Certificate entity){
        if (entity!=null){
            CertificateDTO dto = new CertificateDTO();
            dto.setId(entity.getId());
            dto.setCode(entity.getCode());
            dto.setName(entity.getName());
            dto.setValidSince(entity.getValidSince());
            dto.setValidUntil(entity.getValidUntil());
            if (entity.getCity()!=null){
                CityDTO cityDTO = cityConverter.toDTO(entity.getCity(),false);
                dto.setCity(cityDTO);
            }
            return dto;
        }
        return null;
    }
}
