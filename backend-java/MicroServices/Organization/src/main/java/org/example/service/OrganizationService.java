package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Employee;
import org.example.domain.Organization;
import org.example.domain.dto.OrganizationRequest;
import org.example.domain.dto.OrganizationResponse;
import org.example.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public List<OrganizationResponse> getAllOrganizations() {
        List<Organization> organizations = organizationRepository.findAll();
        return organizations.stream().map(this::mapToOrganizationResponse).toList();
    }

    private OrganizationResponse mapToOrganizationResponse(Organization organization) {
        return OrganizationResponse.builder()
                .id(organization.getId())
                .name(organization.getName())
                .address(organization.getAddress())
                .departments(organization.getDepartments())
                .employees(organization.getEmployees())
                .build();
    }

    @Override
    public void addOrganization(OrganizationRequest organizationRequest) {
        Organization newOrganization = Organization.builder()
                .name(organizationRequest.getName())
                .address(organizationRequest.getAddress())
                .build();
        organizationRepository.save(newOrganization);
    }

    @Override
    public OrganizationResponse getOrganizationById(Long organizationId) throws Exception {
        return findOrganizationById(organizationId, false, false);
    }

    @Override
    public OrganizationResponse getOrganizationByIdWithDepartments(Long organizationId) throws Exception {
        return findOrganizationById(organizationId, true, false);
    }

    @Override
    public OrganizationResponse getOrganizationByIdWithEmployees(Long organizationId) throws Exception {
        return findOrganizationById(organizationId, false, true);
    }

    @Override
    public OrganizationResponse getOrganizationByIdWithDepartmentsAndEmployees(Long organizationId) throws Exception {
        return findOrganizationById(organizationId, true, true);
    }

    private OrganizationResponse findOrganizationById(Long organizationId, boolean withDepartments, boolean withEmployees) throws Exception {
        Optional<Organization> optionalOrganization = organizationRepository.findById(organizationId);
        if (optionalOrganization.isEmpty()) {
            throw new Exception("Organization not found with id: " + organizationId);
        }
        Organization organization = optionalOrganization.get();
        if (withDepartments && organization.getDepartments().isEmpty()) {
            throw new Exception("Organization with id: " + organizationId + " has no departments");
        }
        if (withEmployees && organization.getEmployees().isEmpty()) {
            throw new Exception("Organization with id: " + organizationId + " has no employees");
        }
        return mapToOrganizationResponse(organization);
    }
}
