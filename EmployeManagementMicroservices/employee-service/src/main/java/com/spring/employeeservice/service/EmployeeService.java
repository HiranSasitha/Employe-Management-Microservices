package com.spring.employeeservice.service;

import com.spring.employeeservice.dto.ApiResponseDto;
import com.spring.employeeservice.dto.EmployeeDto;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    ApiResponseDto getEmployeeById(Long id);
}
