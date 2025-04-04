package com.purplemango.app.service.tenants;

import com.purplemango.app.exceptions.TenantDuplicateException;
import com.purplemango.app.model.tenant.AddTenant;
import com.purplemango.app.model.tenant.Tenant;
import com.purplemango.app.model.tenant.UpdateTenant;
import com.purplemango.app.repository.tenants.TenantRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TenantServiceImpl implements TenantService {
    TenantRepository tenantRepository;

    @Autowired
    public TenantServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public Collection<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    @Override
    public Page<Tenant> getAllTenants(Pageable pageable) {
        return tenantRepository.findAll(pageable);
    }

    @Override
    public Tenant getTenantById(final String tenantId) {
        return tenantRepository.findById(tenantId);
    }

    @Override
    public Tenant createTenant(final AddTenant entity) {
        Tenant tenant = tenantRepository.findByCompanyNameAndCompanyCode(entity.companyName(), entity.companyCode());
        if (tenant != null) {
            throw new TenantDuplicateException("Tenant already exists");
        }

        return tenantRepository.save(Tenant.build(entity));
    }

    @Override
    public Tenant createOrUpdateTenant(UpdateTenant tenant) {
        return tenantRepository.createOrUpdateTenant(Tenant.upsert(tenant));
    }
}
