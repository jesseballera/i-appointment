package com.purplemango.app.repository.tenants;

import com.purplemango.app.model.tenant.Tenant;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface TenantRepository {
    Optional<Tenant> findByCompanyNameAndCompanyCode(String companyName, String companyCode);
    Collection<Tenant> findAll();
    Page<Tenant> findAll(Pageable pageable);
    Optional<Tenant> findById(final ObjectId tenantId);
    Optional<Tenant> findByName(String tenantName);
    Tenant  save(Tenant tenant);
    Tenant createOrUpdateTenant(Tenant tenant);
}
