package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Department;
import org.example.domain.Employee;
import org.example.domain.dto.DepartmentRequest;
import org.example.domain.dto.DepartmentResponse;
import org.example.repository.DepartmentRepository;
import org.example.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public DepartmentResponse getDepartmentById(Long departmentId) throws Exception {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isEmpty()) {
            throw new Exception("Department not found with id: " + departmentId);
        }
        return mapToDepartmentResponse(optionalDepartment.get());
    }

    @Override
    public List<DepartmentResponse> getAllDepartmentsByOrganizationId(Long id) {
        List<Department> departments = departmentRepository.findAll();
        departments = departments.stream().filter(d -> Objects.equals(d.getOrganizationId(), id)).collect(Collectors.toList());
        return departments.stream().map(this::mapToDepartmentResponse).toList();
    }

    @Override
    public List<DepartmentResponse> getAllDepartmentsByOrganizationIdWithEmployees(Long id) {
        List<Department> departments = departmentRepository.findAll();
        departments = departments.stream().filter(d -> Objects.equals(d.getOrganizationId(), id)).collect(Collectors.toList());
        departments = departments.stream().filter(d -> !d.getEmployees().isEmpty()).collect(Collectors.toList());
        return departments.stream().map(this::mapToDepartmentResponse).toList();
    }
}
