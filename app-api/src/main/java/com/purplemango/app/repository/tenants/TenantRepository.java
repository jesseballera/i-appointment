package com.purplemango.app.repository.tenants;

import com.purplemango.app.model.tenant.Tenant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface TenantRepository {
    Tenant findByCompanyNameAndCompanyCode(String companyName, String companyCode);
    Collection<Tenant> findAll();
    Page<Tenant> findAll(Pageable pageable);
    Tenant findById(final String tenantId);
    Tenant  save(Tenant tenant);
    Tenant createOrUpdateTenant(Tenant tenant);
}
