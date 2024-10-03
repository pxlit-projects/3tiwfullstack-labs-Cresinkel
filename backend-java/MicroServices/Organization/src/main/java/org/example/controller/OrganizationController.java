package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.dto.OrganizationRequest;
import org.example.service.IOrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final IOrganizationService organizationService;

    @GetMapping
    public ResponseEntity getAllOrganizations() {
        return new ResponseEntity(organizationService.getAllOrganizations(), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrganization(@RequestBody OrganizationRequest organizationRequest) {
         organizationService.addOrganization(organizationRequest);
    }
//    @GetMapping("/{id}") findById
//    @GetMapping("/department/{departmentId}") findByDepartment
//    @GetMapping("/organization/{organizationId}") findByOrganization

}
