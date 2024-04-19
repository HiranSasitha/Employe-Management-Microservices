package com.spring.employeeservice.controller;

import com.spring.employeeservice.dto.EmployeeDto;
import com.spring.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto dto = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable(value = "id")Long id){
        EmployeeDto dto = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
