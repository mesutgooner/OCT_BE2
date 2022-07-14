package com.globits.da.rest;

import com.globits.da.dto.EmployeeCertificateDTO;
import com.globits.da.service.EmployeeCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employeecertificate")
public class RestEmployeeCertificateController {
    @Autowired
    private EmployeeCertificateService employeeCertificateService;
    @PostMapping
    public ResponseEntity<?> save(@RequestBody EmployeeCertificateDTO dto){
        return new ResponseEntity<>(employeeCertificateService.saveOrUpdate(dto), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(employeeCertificateService.getAll(),HttpStatus.OK);
    }
}
