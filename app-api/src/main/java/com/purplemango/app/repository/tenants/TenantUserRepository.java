package com.purplemango.app.repository.tenants;

import com.purplemango.app.model.tenant.Tenant;
import com.purplemango.app.model.tenant.TenantUser;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface TenantUserRepository {
    Collection<TenantUser> findAllUserByTenant(String tenant);
    Page<TenantUser> findAll(String tenant, Pageable pageable);
    TenantUser findByTenantUserById(String tenant, ObjectId id);
    TenantUser findByTenantIdAndUserId(String tenant, ObjectId userId);
    TenantUser save(TenantUser entity);
}
