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
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
//    @Autowired
//    private RestTemplate restTemplate;

//    @Autowired
//    private WebClient webClient;
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
//=============================================================================================
     //communication with rest template

//    ResponseEntity<DepartmentDto> responseEntity= restTemplate.
//    getForEntity("http://localhost:8080/api/department/"+employee.getDepartmentCode(),
//                DepartmentDto.class);
//
//    DepartmentDto departmentDto = responseEntity.getBody();

//=============================================================================================

       // communicate with web client

//  DepartmentDto departmentDto  =   webClient.get()
//               .uri("http://localhost:8080/api/department/"+employee.getDepartmentCode())
//               .retrieve()
//               .bodyToMono(DepartmentDto.class)
//               .block();

//===========================================================================================

        // communicate with spring cloud open feign


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
