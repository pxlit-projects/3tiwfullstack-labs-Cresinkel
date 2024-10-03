package org.example.service;

import org.example.domain.dto.OrganizationRequest;
import org.example.domain.dto.OrganizationResponse;

import java.util.List;

public interface IOrganizationService {
    List<OrganizationResponse> getAllOrganizations();

    void addOrganization(OrganizationRequest organizationRequest);
}
