package com.purplemango.app.service.tenants;

import com.purplemango.app.model.tenant.AddTenantUser;
import com.purplemango.app.model.tenant.Tenant;
import com.purplemango.app.model.tenant.TenantUser;
import com.purplemango.app.repository.tenants.TenantUserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TenantUserServiceImpl implements TenantUserService {

    TenantUserRepository tenantUserRepository;
    TenantService tenantService;

    public TenantUserServiceImpl(TenantUserRepository tenantUserRepository, TenantService tenantService) {
        this.tenantUserRepository = tenantUserRepository;
        this.tenantService = tenantService;
    }

    @Override
    public Collection<TenantUser> getAllTenantUsers(String tenantId) {
        Tenant tenant = tenantService.getTenantById(tenantId);
        return tenantUserRepository.findAllUserByTenant(tenant.companyCode());
    }

    @Override
    public Page<TenantUser> getAllTenantUsers(String tenantId,Pageable pageable) {
        Tenant tenant = tenantService.getTenantById(tenantId);
        return tenantUserRepository.findAll(tenant.companyCode(), pageable);
    }

    @Override
    public List<TenantUser> getTenantUsersByTenantId(String tenantId) {
        return List.of();
    }

    @Override
    public TenantUser getUserById(String tenantId) {
        return null;
    }

    @Override
    public TenantUser createTenantUser(AddTenantUser entity) {
        Tenant tenant = tenantService.getTenantById(entity.tenantId());
        return tenantUserRepository.save(TenantUser.build(entity, tenant.companyCode()));
    }
}
