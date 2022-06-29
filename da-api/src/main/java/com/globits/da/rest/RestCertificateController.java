package com.globits.da.rest;

import com.globits.da.dto.CertificateDTO;
import com.globits.da.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/certificate")
public class RestCertificateController {
    @Autowired
    private CertificateService certificateService;
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CertificateDTO dto){
        return new ResponseEntity<>(certificateService.saveOrUpdate(dto), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody CertificateDTO dto, @PathVariable Long id){
        dto.setId(id);
        return new ResponseEntity<>(certificateService.saveOrUpdate(dto),HttpStatus.OK);
    }
}
