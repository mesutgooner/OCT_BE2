package com.globits.da.service.impl;

import com.globits.da.converter.CertificateConverter;
import com.globits.da.domain.Certificate;
import com.globits.da.dto.CertificateDTO;
import com.globits.da.repository.CertificateRepository;
import com.globits.da.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private CertificateRepository certificateRepository;
    @Autowired
    private CertificateConverter certificateConverter;
    @Override
    public Object saveOrUpdate(CertificateDTO dto) {
        if (dto!=null){
            Certificate entity = null;
            if (dto.getId()!=null){
                entity = certificateRepository.getCertificateById(dto.getId());
            }
            entity = certificateConverter.toEntity(dto,entity);
            if (entity!=null){
                entity=certificateRepository.save(entity);
                return certificateConverter.toDTO(entity);
            }
        }
        return null;
    }
}
