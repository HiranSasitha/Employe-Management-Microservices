package com.spring.organizationservice.repository;

import com.spring.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrganizationRepository extends JpaRepository<Organization,Long> {
    Organization findOrganizationByOrganizationCode(String code);
}
