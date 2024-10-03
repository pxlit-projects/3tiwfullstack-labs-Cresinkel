package org.example.service;

import org.example.domain.Employee;
import org.example.domain.dto.EmployeeRequest;
import org.example.domain.dto.EmployeeResponse;

import java.util.List;

public interface IEmployeeService {
    List<EmployeeResponse> getAllEmployees();

    void addEmployee(EmployeeRequest employeeRequest);

    EmployeeResponse getEmployeeById(Long id) throws Exception;

    List<EmployeeResponse> getAllEmployeesByDepartmentId(Long id);

    List<EmployeeResponse> getAllEmployeesByOrganizationId(Long id);
}
