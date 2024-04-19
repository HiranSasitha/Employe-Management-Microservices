package com.spring.employeeservice.service.impl;

import com.spring.employeeservice.dto.ApiResponseDto;
import com.spring.employeeservice.dto.DepartmentDto;
import com.spring.employeeservice.dto.EmployeeDto;
import com.spring.employeeservice.entity.Employee;
import com.spring.employeeservice.repository.EmployeeRepository;
import com.spring.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstname(),
                employeeDto.getLastname(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );

        Employee saveEmployee = employeeRepository.save(employee);

        EmployeeDto saveDto = new EmployeeDto(
                saveEmployee.getId(),
                saveEmployee.getFirstname(),
                saveEmployee.getLastname(),
                saveEmployee.getEmail(),
                saveEmployee.getDepartmentCode()
        );
        return saveDto;
    }

    @Override
    public ApiResponseDto getEmployeeById(Long id) {
     Employee employee = employeeRepository.findById(id).get();

     //call rest template

    ResponseEntity<DepartmentDto> responseEntity= restTemplate.getForEntity("http://localhost:8080/api/department/"+employee.getDepartmentCode(),
                DepartmentDto.class);

    DepartmentDto departmentDto = responseEntity.getBody();



        EmployeeDto dto = new EmployeeDto(
                employee.getId(),
                employee.getFirstname(),
                employee.getLastname(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        ApiResponseDto apiResponseDto = new ApiResponseDto(dto,departmentDto);
     return apiResponseDto;
    }
}
