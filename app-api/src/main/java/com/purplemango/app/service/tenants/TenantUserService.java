package com.purplemango.app.service.tenants;

import com.purplemango.app.model.tenant.AddTenantUser;
import com.purplemango.app.model.tenant.TenantUser;
import com.purplemango.app.model.tenant.ViewTenantUser;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface TenantUserService {

    Collection<ViewTenantUser> getAllTenantUsers(String tenant);
    Page <ViewTenantUser> getAllTenantUsers(String tenant, Pageable pageable);
    List<ViewTenantUser> getTenantUsersByTenant(final String tenant);
    ViewTenantUser getUserById(final ObjectId tenantId, final String tenant);
    ViewTenantUser createTenantUser(final AddTenantUser entity, final String tenant);
}
