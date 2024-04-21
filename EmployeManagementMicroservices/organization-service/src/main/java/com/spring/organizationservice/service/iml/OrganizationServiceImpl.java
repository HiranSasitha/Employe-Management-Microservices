package com.spring.organizationservice.service.iml;

import com.spring.organizationservice.dto.OrganizationDto;
import com.spring.organizationservice.entity.Organization;
import com.spring.organizationservice.mapper.OrganizationMapper;
import com.spring.organizationservice.repository.OrganizationRepository;
import com.spring.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;
    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.maptoEntity(organizationDto);
        Organization saveOrganization = organizationRepository.save(organization
        );

        OrganizationDto organizationDto1 = OrganizationMapper.maptoDto(saveOrganization);
        return organizationDto1;
    }

    @Override
    public OrganizationDto getbyCode(String code) {

        Organization organization = organizationRepository.findOrganizationByOrganizationCode(code);

        OrganizationDto organizationDto = OrganizationMapper.maptoDto(organization);
        return organizationDto;

    }
}

