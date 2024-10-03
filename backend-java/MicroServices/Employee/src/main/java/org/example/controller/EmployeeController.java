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

    @GetMapping("/{id}")
    public ResponseEntity getEmployeeById(@PathVariable String id) throws Exception {
        Long employeeId = Long.parseLong(id);
        return new ResponseEntity(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity findByDepartment(@PathVariable String departmentId) {
        Long id = Long.parseLong(departmentId);
        return new ResponseEntity(employeeService.getAllEmployeesByDepartmentId(id), HttpStatus.OK);
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity findByOrganization(@PathVariable String organizationId) {
        Long id = Long.parseLong(organizationId);
        return new ResponseEntity(employeeService.getAllEmployeesByOrganizationId(id), HttpStatus.OK);
    }
}
