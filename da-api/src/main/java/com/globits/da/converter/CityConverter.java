package com.globits.da.converter;

import com.globits.da.domain.City;
import com.globits.da.domain.District;
import com.globits.da.dto.CityDTO;
import com.globits.da.dto.DistrictDTO;
import com.globits.da.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CityConverter {
    @Autowired
    private DistrictConverter districtConverter;
    @Autowired
    private DistrictRepository districtRepository;

    public City toEntity(CityDTO dto, City oldEntity, boolean needDistricts) {
        if (dto != null) {
            City entity = new City();
            if (oldEntity != null) {
                entity = oldEntity;
            }
            if (dto.getCode() != null)
                entity.setCode(dto.getCode());
            if (dto.getName() != null)
                entity.setName(dto.getName());
            if (needDistricts) {
                if (!dto.getDistricts().isEmpty()) {
                    List<District> districts = new ArrayList<>();
                    for (DistrictDTO districtDTO : dto.getDistricts()) {
                        District district = null;

                        if (districtDTO.getId() != null) {
                            district = districtRepository.getDistrictById(districtDTO.getId());
                        } //if này để thực hiện việc update district

                        district = districtConverter.toEntity(districtDTO, district, true, true);

                        if (districtDTO.getCity() == null) {
                            district.setCity(entity);
                        } //dùng trong trường hợp khi thao tác thông tin cả city lẫn district để đảm bảo district này luôn thuộc city đang thao tác
                        districts.add(district);
                    }
                    entity.setDistricts(districts);
                }
            }
            return entity;
        }
        return null;
    }

    public CityDTO toDTO(City entity, boolean needDistricts) {
        if (entity != null) {
            CityDTO dto = new CityDTO();
            dto.setId(entity.getId());
            dto.setCode(entity.getCode());
            dto.setName(entity.getName());

            if (needDistricts) {
                if (!entity.getDistricts().isEmpty()) {
                    List<DistrictDTO> districtDTOS = new ArrayList<>();
                    for (District district : entity.getDistricts()) {
                        DistrictDTO districtDTO = districtConverter.toDTO(district, true, true);
                        districtDTOS.add(districtDTO);
                    }
                    dto.setDistricts(districtDTOS);
                }
            }
            return dto;
        }
        return null;
    }
}
