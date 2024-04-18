package com.spring.departmentservice.service;

import com.spring.departmentservice.dto.DepartmentDto;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {

    DepartmentDto saveDepartment (DepartmentDto departmentDto);
}
