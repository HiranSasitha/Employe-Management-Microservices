package com.spring.employeeservice.service.impl;

import com.spring.employeeservice.dto.EmployeeDto;
import com.spring.employeeservice.entity.Employee;
import com.spring.employeeservice.repository.EmployeeRepository;
import com.spring.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstname(),
                employeeDto.getLastname(),
                employeeDto.getEmail()
        );

        Employee saveEmployee = employeeRepository.save(employee);

        EmployeeDto saveDto = new EmployeeDto(
                saveEmployee.getId(),
                saveEmployee.getFirstname(),
                saveEmployee.getLastname(),
                saveEmployee.getEmail()
        );
        return saveDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
     Employee employee = employeeRepository.findById(id).get();

        EmployeeDto dto = new EmployeeDto(
                employee.getId(),
                employee.getFirstname(),
                employee.getLastname(),
                employee.getEmail()
        );
     return dto;
    }
}
