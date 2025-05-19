package com.purplemango.app.controller;

import com.purplemango.app.model.tenant.AddTenantUser;
import com.purplemango.app.model.tenant.TenantUser;
import com.purplemango.app.model.tenant.ViewTenantUser;
import com.purplemango.app.service.tenants.TenantUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/v1/tenant-users")
public class TenantUserController {

    TenantUserService tenantUserService;

    @Autowired
    public TenantUserController(TenantUserService tenantUserService) {
        this.tenantUserService = tenantUserService;
    }

    @GetMapping("/{tenant}/all")
    public  ResponseEntity<?> getAllTenantUsers(@PathVariable("tenant") String tenant) {
        if (tenant == null || tenant.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tenantUserService.getAllTenantUsers(tenant));
    }

    @GetMapping("/{tenant}")
    public ResponseEntity<?> getAllTenants(@PathVariable("tenant") String tenant,
                                           @RequestParam(required = true) int page,
                                           @RequestParam(required = true) int size,
                                           @RequestParam(required = true, value = "q") String  sort,
                                           @RequestParam(required = true) Sort.Direction direction) {
        if (page < 0 || size < 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tenantUserService.getAllTenantUsers(tenant, PageRequest.of(page, size, Sort.by(direction, sort))));
    }

//    @GetMapping("/{tenant}")
//    public ResponseEntity<Collection<ViewTenantUser>> getTenantById(@PathVariable("tenant") String tenant) {
//        return ResponseEntity.ok(tenantUserService.getAllTenantUsers(tenant));
//    }

    @PostMapping("/{tenant}")
    public ResponseEntity<ViewTenantUser> createTenant(@PathVariable("tenant") String tenant, @RequestBody @Validated AddTenantUser entity) {
        if (tenant == null || tenant.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        ViewTenantUser tenantUser = tenantUserService.createTenantUser(entity, tenant);
        return ResponseEntity.ok(tenantUser);
    }
}
