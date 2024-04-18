package com.spring.departmentservice.Controller;

import com.spring.departmentservice.dto.DepartmentDto;
import com.spring.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("save-department")
    public ResponseEntity<?> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto dto = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
