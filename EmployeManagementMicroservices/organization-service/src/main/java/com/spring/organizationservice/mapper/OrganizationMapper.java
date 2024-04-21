package com.spring.organizationservice.mapper;

import com.spring.organizationservice.dto.OrganizationDto;
import com.spring.organizationservice.entity.Organization;

public class OrganizationMapper {

    public static OrganizationDto maptoDto(Organization organization){
        OrganizationDto organizationDto = new OrganizationDto(
                organization.getId(),
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationCode(),
                organization.getCreateDate()

        );
        return organizationDto;
    }

    public static Organization maptoEntity(OrganizationDto organizationDto){

        Organization organization = new Organization(
                organizationDto.getId(),
                organizationDto.getOrganizationName(),
                organizationDto.getOrganizationDescription(),
                organizationDto.getOrganizationCode(),
                organizationDto.getCreateDate()
        );
        return organization;
    }
}
