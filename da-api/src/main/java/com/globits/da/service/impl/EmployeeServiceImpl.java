package com.globits.da.service.impl;

import com.globits.da.converter.EmployeeConverter;
import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.ResponseDTO;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
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
            boolean update = false;
            Employee entity = null;
            if (dto.getId() != null) {
                entity = employeeRepository.getOne(dto.getId());
                update = true;
            }
            ResponseDTO responseDTO = validate(dto, update);
            if (responseDTO != null)
                return responseDTO;
            entity = employeeConverter.toEntity(dto, entity);
            if (entity != null) {
                ResponseDTO responseDTO1 = validateAddress(dto, entity);
                if (responseDTO1 == null) {
                    {
                        entity = employeeRepository.save(entity);
                        return employeeConverter.toDTO(entity);
                    }
                } else
                    return responseDTO1;
            }
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        if (id != null)
            employeeRepository.deleteById(id);
    }

    private ResponseDTO validate(EmployeeDTO dto, boolean update) {
        if (dto != null) {
            Map<String, String> errors = new HashMap<>();
            //check unique code
            if (!update) {
                if (dto.getCode() != null) {
                    List<Employee> employees = employeeRepository.findAllByCode(dto.getCode());
                    if (!employees.isEmpty())
                        errors.put("code", "Code không được trùng");
                }
            }
            //validate name
            if (dto.getName() == null || dto.getName().isEmpty())
                errors.put("name", "Tên không được bỏ trống");
            //validate mail
            if (dto.getEmail() == null || dto.getEmail().isEmpty())
                errors.put("email", "Email không được bỏ trống");
            else {
                try {
                    InternetAddress email = new InternetAddress(dto.getEmail());
                    email.validate();
                } catch (AddressException e) {
                    errors.put("email", "Email không đúng định dạng");
                }
            }
            //validate phone
            if (dto.getPhone() == null)
                errors.put("phone", "Sdt không được bỏ trống");
            else {
                if (!dto.getPhone().matches("[0-9]+")) {
                    errors.put("phone", "Sdt chỉ được nhập số");
                } else if (dto.getPhone().length() != 10) {
                    errors.put("phone", "Sdt phải có 10 chữ số");
                }
            }
            //validate age
            if (dto.getAge() == null)
                errors.put("age", "Tuổi không được bỏ trống");
            else if (dto.getAge() < 0)
                errors.put("age", "Tuổi không được âm");
            if (!errors.isEmpty())
                return new ResponseDTO(dto, "", errors);
        }
        return null;
    }

    private ResponseDTO validateAddress(EmployeeDTO dto, Employee entity) {
        Map<String, String> errors = new HashMap<>();
        if (!entity.getCity().equals(entity.getDistrict().getCity())) {
            errors.put("district", "District không thuộc City");
        }
        if (!entity.getDistrict().equals(entity.getWard().getDistrict())) {
            errors.put("ward", "Ward không thuộc District");
        }
        if (errors.isEmpty())
            return null;
        else
            return new ResponseDTO(dto, "", errors);
    }
}
