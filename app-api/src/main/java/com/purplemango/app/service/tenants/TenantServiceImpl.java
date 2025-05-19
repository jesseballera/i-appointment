package com.purplemango.app.service.tenants;

import com.purplemango.app.exceptions.TenantDuplicateException;
import com.purplemango.app.exceptions.TenantNotFoundException;
import com.purplemango.app.model.tenant.AddTenant;
import com.purplemango.app.model.tenant.Tenant;
import com.purplemango.app.model.tenant.UpdateTenant;
import com.purplemango.app.model.tenant.ViewTenant;
import com.purplemango.app.repository.tenants.TenantRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TenantServiceImpl implements TenantService {
    TenantRepository tenantRepository;

    @Autowired
    public TenantServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Collection<ViewTenant> getAllTenants() {
        Collection<Tenant> tenants = tenantRepository.findAll();
        return tenants.stream().map(ViewTenant::of).collect(Collectors.toList());
    }

    @Override
    public Page<ViewTenant> getAllTenants(Pageable pageable) {
        Page<Tenant> page = tenantRepository.findAll(pageable);
        return page.map(ViewTenant::of);
    }

    @Override
    public ViewTenant getTenantById(final ObjectId tenantId) {
        return ViewTenant
                .of(tenantRepository.findById(tenantId).orElseThrow(() -> new TenantNotFoundException("Tenant not found")));
    }

    @Override
    public ViewTenant getTenantByName(String tenantName) {
        return ViewTenant
                .of(tenantRepository.findByName(tenantName).orElseThrow(() -> new TenantNotFoundException("Tenant not found")));
    }

    @Override
    public ViewTenant createTenant(final AddTenant entity) {
        Optional<Tenant> tenant = tenantRepository.findByCompanyNameAndCompanyCode(entity.companyName(), entity.companyCode());
        if (tenant.isPresent()) {
            throw new TenantDuplicateException("Tenant already exists");
        }
        return ViewTenant.of(tenantRepository.save(Tenant.build(entity)));
    }

    @Override
    public ViewTenant createOrUpdateTenant(UpdateTenant tenant, ObjectId entityId) {
        return ViewTenant
                .of(tenantRepository.createOrUpdateTenant(Tenant.upsert(tenant, entityId)));
    }
}
