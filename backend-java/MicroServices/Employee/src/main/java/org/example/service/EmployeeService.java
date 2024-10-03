package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Employee;
import org.example.domain.dto.EmployeeRequest;
import org.example.domain.dto.EmployeeResponse;
import org.example.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::mapToEmployeeResponse).toList();
    }

    private EmployeeResponse mapToEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .age(employee.getAge())
                .name(employee.getName())
                .position(employee.getPosition())
                .organizationId(employee.getOrganizationId())
                .employeeId(employee.getId())
                .departementId(employee.getDepartementId())
                .build();
    }

    @Override
    public void addEmployee(EmployeeRequest employeeRequest) {
        Employee newEmployee = Employee.builder()
                .age(employeeRequest.getAge())
                .position(employeeRequest.getPosition())
                .name(employeeRequest.getName())
                .build();
        employeeRepository.save(newEmployee);
    }
}
