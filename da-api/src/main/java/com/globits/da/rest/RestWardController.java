package com.globits.da.rest;

import com.globits.da.dto.WardDTO;
import com.globits.da.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ward")
public class RestWardController {
    @Autowired
    private WardService wardService;
    @PostMapping
    public ResponseEntity<?> save(@RequestBody WardDTO dto){
        return new ResponseEntity<>(wardService.saveOrUpdate(dto), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody WardDTO dto, @PathVariable Long id){
        dto.setId(id);
        return new ResponseEntity<>(wardService.saveOrUpdate(dto),HttpStatus.OK);
    }
}
