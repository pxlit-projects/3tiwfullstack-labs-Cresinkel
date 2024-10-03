package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Employee;
import org.example.domain.dto.EmployeeRequest;
import org.example.domain.dto.EmployeeResponse;
import org.example.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public EmployeeResponse getEmployeeById(Long id) throws Exception {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            throw new Exception("Employee not found with id: " + id);
        }
        return mapToEmployeeResponse(optionalEmployee.get());
    }

    @Override
    public List<EmployeeResponse> getAllEmployeesByDepartmentId(Long id) {
        List<Employee> employees = employeeRepository.findAll();
        employees = employees.stream().filter(e -> Objects.equals(e.getDepartementId(), id)).collect(Collectors.toList());
        return employees.stream().map(this::mapToEmployeeResponse).toList();
    }

    @Override
    public List<EmployeeResponse> getAllEmployeesByOrganizationId(Long id) {
        List<Employee> employees = employeeRepository.findAll();
        employees = employees.stream().filter(e -> Objects.equals(e.getOrganizationId(), id)).collect(Collectors.toList());
        return employees.stream().map(this::mapToEmployeeResponse).toList();
    }
}
