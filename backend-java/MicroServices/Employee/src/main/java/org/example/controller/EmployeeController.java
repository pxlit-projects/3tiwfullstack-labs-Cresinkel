package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.dto.EmployeeRequest;
import org.example.service.IEmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity getEmployees() {
        return new ResponseEntity(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@RequestBody EmployeeRequest employeeRequest) {
         employeeService.addEmployee(employeeRequest);
    }
//    @GetMapping("/{id}") findById
//    @GetMapping("/department/{departmentId}") findByDepartment
//    @GetMapping("/organization/{organizationId}") findByOrganization

}
