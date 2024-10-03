package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Organization;
import org.example.domain.dto.OrganizationRequest;
import org.example.domain.dto.OrganizationResponse;
import org.example.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
