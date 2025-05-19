package com.purplemango.app.service.tenants;


import com.purplemango.app.model.tenant.AddTenant;
import com.purplemango.app.model.tenant.Tenant;
import com.purplemango.app.model.tenant.UpdateTenant;
import com.purplemango.app.model.tenant.ViewTenant;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface TenantService {

    Collection<ViewTenant> getAllTenants();
    Page <ViewTenant> getAllTenants(Pageable pageable);
    ViewTenant getTenantById(final ObjectId tenantId);
    ViewTenant getTenantByName(final String tenantName);
    ViewTenant createTenant(final AddTenant tenant);
    ViewTenant createOrUpdateTenant(final UpdateTenant tenant, ObjectId entityId);
}
