package org.example.service;

import org.example.domain.dto.DepartmentRequest;
import org.example.domain.dto.DepartmentResponse;

import java.util.List;

public interface IDepartmentService {
    List<DepartmentResponse> getAllDepartments();

    void addDepartment(DepartmentRequest departmentRequest);

    DepartmentResponse getDepartmentById(Long departmentId) throws Exception;

    List<DepartmentResponse> getAllDepartmentsByOrganizationId(Long id);

    List<DepartmentResponse> getAllDepartmentsByOrganizationIdWithEmployees(Long id);
}
