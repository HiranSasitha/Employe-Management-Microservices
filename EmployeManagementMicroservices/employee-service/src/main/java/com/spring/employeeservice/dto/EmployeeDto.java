package com.spring.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDto {
    private Long id;
    private String firstname;
    private String lastname;

    private String email;
}

