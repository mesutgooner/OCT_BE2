package com.globits.da.converter;

import com.globits.da.domain.City;
import com.globits.da.domain.District;
import com.globits.da.domain.Employee;
import com.globits.da.domain.Ward;
import com.globits.da.dto.CityDTO;
import com.globits.da.dto.DistrictDTO;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.WardDTO;
import com.globits.da.repository.CityRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {
    @Autowired
    private CityConverter cityConverter;
    @Autowired
    private DistrictConverter districtConverter;
    @Autowired
    private WardConverter wardConverter;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private WardRepository wardRepository;

    public Employee toEntity(EmployeeDTO dto, Employee oldEntity) {
        if (dto != null) {
            Employee entity = new Employee();
            if (oldEntity != null)
                entity = oldEntity;
            if (dto.getCode() != null)
                entity.setCode(dto.getCode());
            if (dto.getName()!=null)
                entity.setName(dto.getName());
            if (dto.getEmail() != null)
                entity.setEmail(dto.getEmail());
            if (dto.getPhone() != null)
                entity.setPhone(dto.getPhone());
            if (dto.getAge() != null)
                entity.setAge(dto.getAge());
            if (dto.getCity() != null) {
                City city = null;
                if (dto.getCity().getId() != null) {
                    city = cityRepository.getCityById(dto.getCity().getId());
                }
                entity.setCity(cityConverter.toEntity(dto.getCity(),city,false));
            }
            if (dto.getDistrict()!=null){
                District district = null;
                if (dto.getDistrict().getId()!=null){
                    district = districtRepository.getDistrictById(dto.getDistrict().getId());
                }
                entity.setDistrict(districtConverter.toEntity(dto.getDistrict(),district,true,false));
            }
            if (dto.getWard()!=null){
                Ward ward = null;
                if (dto.getWard().getId()!=null){
                    ward = wardRepository.getWardById(dto.getWard().getId());
                }
                entity.setWard(wardConverter.toEntity(dto.getWard(),ward,true));
            }
            return entity;
        }
        return null;
    }

    public EmployeeDTO toDTO(Employee entity){
        if (entity!=null){
            EmployeeDTO dto = new EmployeeDTO();
            dto.setId(entity.getId());
            dto.setCode(entity.getCode());
            dto.setName(entity.getName());
            dto.setEmail(entity.getEmail());
            dto.setPhone(entity.getPhone());
            dto.setAge(entity.getAge());
            CityDTO cityDTO = cityConverter.toDTO(entity.getCity(),true);
            dto.setCity(cityDTO);
            DistrictDTO districtDTO = districtConverter.toDTO(entity.getDistrict(),false,true);
            dto.setDistrict(districtDTO);
            WardDTO wardDTO = wardConverter.toDTO(entity.getWard(),false);
            dto.setWard(wardDTO);
            return dto;
        }
        return null;
    }
}
