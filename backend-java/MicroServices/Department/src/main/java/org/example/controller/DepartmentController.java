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
    public ResponseEntity getEmployees() {
        return new ResponseEntity(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@RequestBody DepartmentRequest departmentRequest) {
         departmentService.addDepartment(departmentRequest);
    }

//    @GetMapping("/{id}") findById
//    @GetMapping("/department/{departmentId}") findByDepartment
//    @GetMapping("/organization/{organizationId}") findByOrganization

}
