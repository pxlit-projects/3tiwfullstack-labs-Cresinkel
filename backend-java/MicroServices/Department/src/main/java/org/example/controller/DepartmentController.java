package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.dto.DepartmentRequest;
import org.example.domain.dto.EmployeeRequest;
import org.example.service.IDepartmentService;
import org.example.service.IEmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController
{

    private final IDepartmentService departmentService;

    @GetMapping
    public ResponseEntity getDepartments() {
        return new ResponseEntity(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addDepartment(@RequestBody DepartmentRequest departmentRequest) {
         departmentService.addDepartment(departmentRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity getDepartmentById(@PathVariable String id) throws Exception {
        Long departmentId = Long.parseLong(id);
        return new ResponseEntity(departmentService.getDepartmentById(departmentId), HttpStatus.OK);
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity findByOrganization(@PathVariable String organizationId) {
        Long id = Long.parseLong(organizationId);
        return new ResponseEntity(departmentService.getAllDepartmentsByOrganizationId(id), HttpStatus.OK);
    }

    @GetMapping("/organization/{organizationId}/with-employees")
    public ResponseEntity findByOrganizationWithEmployees(@PathVariable String organizationId) {
        Long id = Long.parseLong(organizationId);
        return new ResponseEntity(departmentService.getAllDepartmentsByOrganizationIdWithEmployees(id), HttpStatus.OK);
    }
}
