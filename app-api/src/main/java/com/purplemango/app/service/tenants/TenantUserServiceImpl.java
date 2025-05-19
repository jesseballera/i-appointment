package com.purplemango.app.service.tenants;

import com.purplemango.app.exceptions.TenantNotFoundException;
import com.purplemango.app.model.tenant.*;
import com.purplemango.app.repository.tenants.TenantRepository;
import com.purplemango.app.repository.tenants.TenantUserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TenantUserServiceImpl implements TenantUserService {

    TenantUserRepository tenantUserRepository;
    TenantRepository tenantRepository;

    public TenantUserServiceImpl(TenantUserRepository tenantUserRepository, TenantRepository tenantRepository) {
        this.tenantUserRepository = tenantUserRepository;
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Collection<ViewTenantUser> getAllTenantUsers(String tenant) {
        Collection<TenantUser> tenantUsers = tenantUserRepository.findAllUserByTenant(tenant);
        return tenantUsers.stream().map(ViewTenantUser::of).collect(Collectors.toList());
    }

    @Override
    public Page<ViewTenantUser> getAllTenantUsers(String tenant,Pageable pageable) {
        Page<TenantUser> page = tenantUserRepository.findAll(tenant, pageable);
        return page.map(ViewTenantUser::of);
    }

    @Override
    public List<ViewTenantUser> getTenantUsersByTenant(String tenant) {
        return List.of();
    }

    @Override
    public ViewTenantUser getUserById(ObjectId entityId, final String tenant) {
        Tenant entity = tenantRepository.findByName(tenant).orElseThrow(() -> new TenantNotFoundException("Tenant not found"));
        return ViewTenantUser.of(tenantUserRepository.findByTenantUserById(tenant, entityId));
    }

    @Override
    public ViewTenantUser createTenantUser(AddTenantUser entity, String tenantName) {
        Tenant tenant = tenantRepository.findByName(tenantName).orElseThrow(() -> new TenantNotFoundException("Tenant not found"));
        TenantUser tenantUser = tenantUserRepository.save(TenantUser.build(entity, tenant));
//                TenantUser.build(entity, tenant);
        return null;
//                ViewTenantUser
//                .of(tenantUserRepository.save(TenantUser.build(entity, tenant)));
    }
}
