package com.purplemango.app.service.tenants;

import com.purplemango.app.model.tenant.AddTenantUser;
import com.purplemango.app.model.tenant.TenantUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface TenantUserService {

    Collection<TenantUser> getAllTenantUsers(String tenantId);
    Page <TenantUser> getAllTenantUsers(String tenantId, Pageable pageable);
    List<TenantUser> getTenantUsersByTenantId(final String tenantId);
    TenantUser getUserById(final String tenantId);
    TenantUser createTenantUser(final AddTenantUser tenant);
}
