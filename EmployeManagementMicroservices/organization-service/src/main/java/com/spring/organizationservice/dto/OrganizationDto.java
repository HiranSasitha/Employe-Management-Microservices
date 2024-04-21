package com.spring.organizationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {
    private Long id;

    private String organizationName;
    private String organizationDescription;

    private String organizationCode;

    private LocalDate createDate;
}
