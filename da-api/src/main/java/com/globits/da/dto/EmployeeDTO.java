package com.globits.da.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDTO {
    private Long id;
    @NotBlank(message = "Code không được để trống")
    @Size(min = 6, max = 10, message = "Code phải có tối thiểu 6 ký tự và tối đa 10 ký tự")
    private String code;
    @NotBlank(message = "Name không được để trống")
    private String name;
    @Email(message = "Không đúng định dạng email")
    private String email;
    @Length(min = 10, max = 10, message = "Số điện thoại phải có 10 ký tự")
    private String phone;
    @Min(value = 0,message = "Tuổi không được âm")
    private Integer age;
    private CityDTO city;
    private DistrictDTO district;
    private WardDTO ward;
    private List<CertificateDTO> certificates = new ArrayList<>();

    public EmployeeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

    public DistrictDTO getDistrict() {
        return district;
    }

    public void setDistrict(DistrictDTO district) {
        this.district = district;
    }

    public WardDTO getWard() {
        return ward;
    }

    public void setWard(WardDTO ward) {
        this.ward = ward;
    }

    public List<CertificateDTO> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<CertificateDTO> certificates) {
        this.certificates = certificates;
    }
}
