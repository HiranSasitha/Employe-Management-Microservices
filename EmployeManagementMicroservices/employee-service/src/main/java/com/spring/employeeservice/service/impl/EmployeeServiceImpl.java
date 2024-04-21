package com.spring.employeeservice.service.impl;

import com.spring.employeeservice.dto.ApiResponseDto;
import com.spring.employeeservice.dto.DepartmentDto;
import com.spring.employeeservice.dto.EmployeeDto;
import com.spring.employeeservice.dto.OrganizationDto;
import com.spring.employeeservice.entity.Employee;
import com.spring.employeeservice.repository.EmployeeRepository;
import com.spring.employeeservice.service.APIClient;
import com.spring.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);//check to come the method inside
    @Autowired
    private EmployeeRepository employeeRepository;
//    @Autowired
//   private RestTemplate restTemplate;

    @Autowired
    private APIClient apiClient;

    @Autowired
   private WebClient webClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstname(),
                employeeDto.getLastname(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode(),
                employeeDto.getOrganizationCode()
        );

        Employee saveEmployee = employeeRepository.save(employee);

        EmployeeDto saveDto = new EmployeeDto(
                saveEmployee.getId(),
                saveEmployee.getFirstname(),
                saveEmployee.getLastname(),
                saveEmployee.getEmail(),
                saveEmployee.getDepartmentCode(),
                saveEmployee.getOrganizationCode()
        );
        return saveDto;
    }

   // @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Override
    public ApiResponseDto getEmployeeById(Long id) {
        logger.info("inside getemployebyid");
     Employee employee = employeeRepository.findById(id).get();
//=============================================================================================
     //communication with rest template

//    ResponseEntity<DepartmentDto> responseEntity= restTemplate.
//    getForEntity("http://localhost:8080/api/department/"+employee.getDepartmentCode(),
//                DepartmentDto.class);
//
//    DepartmentDto departmentDto = responseEntity.getBody();

//=============================================================================================

        //communicate with web client

  DepartmentDto departmentDto  =   webClient.get()
               .uri("http://localhost:8080/api/department/"+employee.getDepartmentCode())
               .retrieve()
               .bodyToMono(DepartmentDto.class)
               .block();

        OrganizationDto organizationDto  =   webClient.get()
                .uri("http://localhost:8083/api/organization/"+employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

//===========================================================================================

        // communicate with spring cloud open feign
//DepartmentDto departmentDto = apiClient.getDepartmentById(employee.getDepartmentCode());

        EmployeeDto dto = new EmployeeDto(
                employee.getId(),
                employee.getFirstname(),
                employee.getLastname(),
                employee.getEmail(),
                employee.getDepartmentCode(),
                employee.getOrganizationCode()
        );

        ApiResponseDto apiResponseDto = new ApiResponseDto(dto,departmentDto,organizationDto);
     return apiResponseDto;
    }

    public ApiResponseDto getDefaultDepartment(Long id,Exception exception) {
        logger.info("inside getdefaultdepartment");
        Employee employee = employeeRepository.findById(id).get();


    DepartmentDto departmentDto = new DepartmentDto();
    departmentDto.setId(0L);
    departmentDto.setDepartmentName("Default Department");
    departmentDto.setDepartmentCode(employee.getDepartmentCode());
    departmentDto.setDepartmentDescription("Develop Department");

    OrganizationDto organizationDto = new OrganizationDto();
    organizationDto.setOrganizationName("default organization");
    organizationDto.setOrganizationDescription("Default");
    organizationDto.setOrganizationCode(employee.getOrganizationCode());

        EmployeeDto dto = new EmployeeDto(
                employee.getId(),
                employee.getFirstname(),
                employee.getLastname(),
                employee.getEmail(),
                employee.getDepartmentCode(),
                employee.getOrganizationCode()
        );

        ApiResponseDto apiResponseDto = new ApiResponseDto(dto,departmentDto,organizationDto);
        return apiResponseDto;
    }
}
