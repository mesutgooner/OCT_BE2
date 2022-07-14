package com.globits.da.service.impl;

import com.globits.da.common.Error;
import com.globits.da.converter.EmployeeConverter;
import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.ResponseDTO;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeConverter employeeConverter;

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    @Override
    public Object saveOrUpdate(EmployeeDTO dto) {
        if (dto != null) {
            List<ResponseDTO> errors = new ArrayList<>();
            boolean update = false;
            Employee entity = null;
            if (dto.getId() != null) {
                entity = employeeRepository.getOne(dto.getId());
                update = true;
            }
            errors = validate(dto, update);
            if (errors != null && !errors.isEmpty())
                return errors;
            entity = employeeConverter.toEntity(dto, entity);
            if (entity != null) {
                errors=validateAddress(entity);
                if (errors == null || errors.isEmpty()) {
                    {
                        entity = employeeRepository.save(entity);
                        return employeeConverter.toDTO(entity);
                    }
                } else
                    return errors;
            }
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        if (id != null)
            employeeRepository.deleteById(id);
    }

    private List<ResponseDTO> validate(EmployeeDTO dto, boolean update) {
        if (dto != null) {
            List<ResponseDTO> errors = new ArrayList<>();
            if (dto.getCode() == null || dto.getCode().isEmpty() || !StringUtils.hasText(dto.getCode()))
                errors.add(new ResponseDTO(dto.getCode(), Error.CODE_IS_NULL.getCode(), Error.CODE_IS_NULL.getMessage()));
            else {
                if (dto.getCode().length() < 6) {
                    errors.add(new ResponseDTO(dto.getCode(), Error.CODE_LENGTH_INVALID.getCode(), Error.CODE_LENGTH_INVALID.getMessage()));
                }
                if (StringUtils.containsWhitespace(dto.getCode()))
                    errors.add(new ResponseDTO(dto.getCode(), Error.CODE_HAS_SPACE.getCode(), Error.CODE_HAS_SPACE.getMessage()));
                //check unique code
                if (!update) {
                    List<Employee> employees = employeeRepository.findAllByCode(dto.getCode());
                    if (!employees.isEmpty())
                        errors.add(new ResponseDTO(dto.getCode(), Error.CODE_EXISTED.getCode(), Error.CODE_EXISTED.getMessage()));
                }
            }

            //validate name
            if (dto.getName() == null || dto.getName().isEmpty()|| !StringUtils.hasText(dto.getName()))
                errors.add(new ResponseDTO(dto.getName(), Error.NAME_IS_NULL.getCode(), Error.NAME_IS_NULL.getMessage()));
            //validate mail
            if (dto.getEmail() == null || dto.getEmail().isEmpty()|| !StringUtils.hasText(dto.getEmail()))
                errors.add(new ResponseDTO(dto.getEmail(), Error.EMAIL_IS_NULL.getCode(), Error.EMAIL_IS_NULL.getMessage()));
            else {
                try {
                    InternetAddress email = new InternetAddress(dto.getEmail());
                    email.validate();
                } catch (AddressException e) {
                    errors.add(new ResponseDTO(dto.getEmail(), Error.EMAIL_INVALID_FORMAT.getCode(), Error.EMAIL_INVALID_FORMAT.getMessage()));
                }
            }
            //validate phone
            if (dto.getPhone() == null || dto.getPhone().isEmpty()|| !StringUtils.hasText(dto.getPhone()))
                errors.add(new ResponseDTO(dto.getPhone(), Error.PHONE_IS_NULL.getCode(), Error.PHONE_IS_NULL.getMessage()));
            else {
                if (!dto.getPhone().matches("[0-9]+")) {
                    errors.add(new ResponseDTO(dto.getPhone(), Error.PHONE_NAN.getCode(), Error.PHONE_NAN.getMessage()));
                } else if (dto.getPhone().length() > 11 || dto.getPhone().length()<10) {
                    errors.add(new ResponseDTO(dto.getPhone(), Error.PHONE_LENGTH_INVALID.getCode(), Error.PHONE_LENGTH_INVALID.getMessage()));
                }
            }
            //validate age
            if (dto.getAge() == null)
                errors.add(new ResponseDTO(dto.getAge(), Error.AGE_IS_NULL.getCode(), Error.AGE_IS_NULL.getMessage()));
            else if (dto.getAge() < 0 && !(dto.getAge() instanceof Integer))
                errors.add(new ResponseDTO(dto.getAge(), Error.AGE_IS_INVALID.getCode(), Error.AGE_IS_INVALID.getMessage()));
            if (!errors.isEmpty())
                return errors;
        }
        return null;
    }

    private List<ResponseDTO> validateAddress(Employee entity) {
        List<ResponseDTO> errors = new ArrayList<>();
        EmployeeDTO employeeDTO = employeeConverter.toDTO(entity);
        if (!entity.getCity().equals(entity.getDistrict().getCity())) {
            errors.add(new ResponseDTO(employeeDTO.getDistrict(), Error.DISTRICT_CITY_INVALID.getCode(), Error.DISTRICT_CITY_INVALID.getMessage()));
        }
        if (!entity.getDistrict().equals(entity.getWard().getDistrict())) {
            errors.add(new ResponseDTO(employeeDTO.getWard(), Error.WARD_DISTRICT_INVALID.getCode(), Error.WARD_DISTRICT_INVALID.getMessage()));
        }
        if (errors.isEmpty())
            return null;
        else
            return errors;
    }

    @Override
    public Object importFromExcel(MultipartFile file) {
        try {
            FileInputStream inputStream = new FileInputStream((File) file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
