package com.purplemango.app.service.tenants;


import com.purplemango.app.model.tenant.AddTenant;
import com.purplemango.app.model.tenant.Tenant;
import com.purplemango.app.model.tenant.UpdateTenant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface TenantService {

    Collection<Tenant> getAllTenants();
    Page <Tenant> getAllTenants(Pageable pageable);
    Tenant getTenantById(final String tenantId);
    Tenant createTenant(final AddTenant tenant);
    Tenant createOrUpdateTenant(final UpdateTenant tenant);
}
