package com.globits.da.converter;

import com.globits.da.domain.District;
import com.globits.da.domain.Ward;
import com.globits.da.dto.WardDTO;
import com.globits.da.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WardConverter {
    @Autowired
    private DistrictConverter districtConverter;
    @Autowired
    private DistrictRepository districtRepository;

    public Ward toEntity(WardDTO dto, Ward oldEntity, boolean needDistrict) {
        if (dto != null) {
            Ward entity = new Ward();
            if (oldEntity != null) {
                entity = oldEntity;
            }
            if (dto.getCode() != null)
                entity.setCode(dto.getCode());
            if (dto.getName() != null)
                entity.setName(dto.getName());
            if (needDistrict) {
                if (dto.getDistrict() != null) {
                    District district = null;
                    if (dto.getDistrict().getId() != null) {
                        district = districtRepository.getDistrictById(dto.getDistrict().getId());
                    }
                    district = districtConverter.toEntity(dto.getDistrict(), district, true, false);
                    entity.setDistrict(district);
                }
            }
            return entity;
        }
        return null;
    }

    public WardDTO toDTO(Ward entity, boolean needDistrict) {
        if (entity != null) {
            WardDTO dto = new WardDTO();
            dto.setId(entity.getId());
            dto.setCode(entity.getCode());
            dto.setName(entity.getName());
            District district = entity.getDistrict();
            if (needDistrict && district != null)
                dto.setDistrict(districtConverter.toDTO(district, true, false));
            return dto;
        }
        return null;
    }
}
