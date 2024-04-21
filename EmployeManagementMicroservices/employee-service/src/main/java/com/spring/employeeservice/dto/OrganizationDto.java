package com.spring.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrganizationDto {
    private Long id;

    private String organizationName;
    private String organizationDescription;

    private String organizationCode;

    private LocalDate createDate;
}
