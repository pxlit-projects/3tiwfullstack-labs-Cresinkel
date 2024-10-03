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

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id) throws Exception {
        Long organizationId = Long.parseLong(id);
        return new ResponseEntity(organizationService.getOrganizationById(organizationId), HttpStatus.OK);
    }

    @GetMapping("/{id}/with-departments")
    public ResponseEntity findByIdWithDepartments(@PathVariable String id) throws Exception {
        Long organizationId = Long.parseLong(id);
        return new ResponseEntity(organizationService.getOrganizationByIdWithDepartments(organizationId), HttpStatus.OK);
    }

    @GetMapping("/{id}/with-employees")
    public ResponseEntity findByIdWithEmployees(@PathVariable String id) throws Exception {
        Long organizationId = Long.parseLong(id);
        return new ResponseEntity(organizationService.getOrganizationByIdWithEmployees(organizationId), HttpStatus.OK);
    }

    @GetMapping("/{id}/with-departments-and-employees")
    public ResponseEntity findByIdWithDepartmentsAndEmployees(@PathVariable String id) throws Exception {
        Long organizationId = Long.parseLong(id);
        return new ResponseEntity(organizationService.getOrganizationByIdWithDepartmentsAndEmployees(organizationId), HttpStatus.OK);
    }
}
