package com.purplemango.app.controller;

import com.purplemango.app.model.tenant.AddTenantUser;
import com.purplemango.app.model.tenant.TenantUser;
import com.purplemango.app.service.tenants.TenantUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/v1/tenant-users")
public class TenantUserController {

    TenantUserService tenantUserService;

    @Autowired
    public TenantUserController(TenantUserService tenantUserService) {
        this.tenantUserService = tenantUserService;
    }

    @GetMapping("/{tenant-id}/all")
    public  ResponseEntity<?> getAllTenantUsers(@PathVariable("tenant-id") String tenantId) {
        if (tenantId == null || tenantId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tenantUserService.getAllTenantUsers(tenantId));
    }

    @GetMapping("/{tenant-id}")
    public ResponseEntity<?> getAllTenants(@PathVariable("tenant-id") String tenant,
                                           @RequestParam(required = true) int page,
                                           @RequestParam(required = true) int size,
                                           @RequestParam(required = true, value = "q") String  sort,
                                           @RequestParam(required = true) Sort.Direction direction) {
        if (page < 0 || size < 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tenantUserService.getAllTenantUsers(tenant, PageRequest.of(page, size, Sort.by(direction, sort))));
    }

//    @GetMapping("/{tenantId}")
//    public ResponseEntity<?> getTenantById(@PathVariable UUID tenantId) {
//        return ResponseEntity.ok(tenantService.getTenantById(tenantId));
//    }

    @PostMapping("/{tenant-id}")
    public ResponseEntity<?> createTenant(@PathVariable("tenant-id") String tenantId, @RequestBody @Validated AddTenantUser entity) {
        if (tenantId == null || tenantId.isEmpty() || !tenantId.equals(entity.tenantId())) {
            return ResponseEntity.badRequest().build();
        }
        TenantUser tenantUser = tenantUserService.createTenantUser(entity);
        return ResponseEntity.ok(tenantUser);
    }
}
