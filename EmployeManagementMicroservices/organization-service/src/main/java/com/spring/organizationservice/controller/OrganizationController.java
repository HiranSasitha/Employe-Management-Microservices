package com.spring.organizationservice.controller;

import com.spring.organizationservice.dto.OrganizationDto;
import com.spring.organizationservice.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<?> saveOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto organizationDto1 = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(organizationDto1, HttpStatus.CREATED);
    }

    @GetMapping("{code}")
    public ResponseEntity<?> getByCode(@PathVariable(value ="code") String code){
        OrganizationDto organizationDto = organizationService.getbyCode(code);
        return new ResponseEntity<>(organizationDto,HttpStatus.OK);
    }
}
