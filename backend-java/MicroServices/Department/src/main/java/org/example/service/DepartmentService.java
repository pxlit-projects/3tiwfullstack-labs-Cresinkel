package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Department;
import org.example.domain.Employee;
import org.example.domain.dto.DepartmentRequest;
import org.example.domain.dto.DepartmentResponse;
import org.example.repository.DepartmentRepository;
import org.example.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(this::mapToDepartmentResponse).toList();
    }

    private DepartmentResponse mapToDepartmentResponse(Department department) {
        return DepartmentResponse.builder()
                .name(department.getName())
                .position(department.getPosition())
                .organizationId(department.getOrganizationId())
                .departmentId(department.getId())
                .employees(department.getEmployees())
                .build();
    }

    @Override
    public void addDepartment(DepartmentRequest departmentRequest) {
        Department newDepartment = Department.builder()
                .position(departmentRequest.getPosition())
                .name(departmentRequest.getName())
                .build();
        departmentRepository.save(newDepartment);
    }
}
