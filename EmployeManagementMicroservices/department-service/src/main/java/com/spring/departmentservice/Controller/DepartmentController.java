package com.spring.departmentservice.Controller;

import com.spring.departmentservice.dto.DepartmentDto;
import com.spring.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{department-code}")
    public ResponseEntity<?> getDepartmentById(@PathVariable(value = "department-code") String code){

        DepartmentDto dto = departmentService.getDepartmentByCode(code);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
