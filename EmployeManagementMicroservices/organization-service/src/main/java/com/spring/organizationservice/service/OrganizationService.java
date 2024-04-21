package com.spring.organizationservice.service;

import com.spring.organizationservice.dto.OrganizationDto;
import org.springframework.stereotype.Service;

@Service
public interface OrganizationService {
    OrganizationDto saveOrganization(OrganizationDto organizationDto);

    OrganizationDto getbyCode(String code);
}
