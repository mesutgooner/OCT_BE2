package com.globits.da.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotBlank(message = "Code không được để trống")
    @Size(min = 6, max = 10, message = "Code phải có tối thiểu 6 ký tự và tối đa 10 ký tự")
    private String code;
    @NotBlank(message = "Name không được để trống")
    private String name;
    @Email(message = "Không đúng định dạng email")
    private String email;
    @Length(min = 10, max = 10, message = "Số điện thoại phải có 10 ký tự")
    private String phone;
    @Min(value = 0, message = "Tuổi không được âm")
    private Integer age;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private District district;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ward_id", referencedColumnName = "id")
    private Ward ward;
    @ManyToMany
    @JoinTable(name = "employee_certificate",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "certificate_id"))
    private List<Certificate> certificates = new ArrayList<>();


    public Employee() {
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }
}
