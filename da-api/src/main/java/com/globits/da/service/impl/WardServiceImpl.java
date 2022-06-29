package com.globits.da.service.impl;

import com.globits.da.converter.WardConverter;
import com.globits.da.domain.Ward;
import com.globits.da.dto.WardDTO;
import com.globits.da.repository.WardRepository;
import com.globits.da.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WardServiceImpl implements WardService {
    @Autowired
    private WardRepository wardRepository;
    @Autowired
    private WardConverter wardConverter;

    @Override
    public WardDTO saveOrUpdate(WardDTO dto) {
        if (dto != null) {
            Ward entity = null;
            if (dto.getId() != null) {
                entity = wardRepository.getOne(dto.getId());
            }
            entity = wardConverter.toEntity(dto, entity, true);
            if (entity != null)
                entity = wardRepository.save(entity);
            return wardConverter.toDTO(entity,true);
        }
        return null;
    }
}
