package com.purplemango.app.repository.tenants;

import com.purplemango.app.model.tenant.Tenant;
import com.purplemango.app.model.tenant.TenantUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface TenantUserRepository {
    Collection<TenantUser> findAllUserByTenant(String tenantId);
    Page<TenantUser> findAll(String tenantId, Pageable pageable);
    Tenant findByTenantId(String tenantId);
    TenantUser findByTenantIdAndUserId(String tenantId, String userId);
    TenantUser save(TenantUser entity);
}
