package com.globits.da.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_certificate")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinTable(name = "employee_certificate", joinColumns = @JoinColumn(name = "city_id"), inverseJoinColumns = @JoinColumn(name = "certificate_id"))
    private City city;

    public Certificate() {
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
