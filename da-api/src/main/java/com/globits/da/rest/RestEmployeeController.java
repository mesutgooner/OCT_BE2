package com.globits.da.rest;

import com.globits.da.dto.EmployeeDTO;
import com.globits.da.dto.ResponseDTO;
import com.globits.da.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class RestEmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(employeeService.getAll(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody EmployeeDTO dto){
        return getResponseEntity(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody EmployeeDTO dto, @PathVariable Long id){
        dto.setId(id);
        return getResponseEntity(dto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        employeeService.delete(id);
    }
    @PostMapping("/import")
    public ResponseEntity<?> importFromExcel(@RequestParam("file")MultipartFile file){
        return new ResponseEntity<>(employeeService.importFromExcel(file),HttpStatus.OK);
    }

    private ResponseEntity<?> getResponseEntity(EmployeeDTO dto) {
        Object result = employeeService.saveOrUpdate(dto);
        ResponseEntity responseEntity = null;
        if (result instanceof EmployeeDTO)
            responseEntity =  new ResponseEntity<>(result, HttpStatus.OK);
        else {
            responseEntity = new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
