package com.purplemango.app.controller;

import com.google.protobuf.DescriptorProtos;
import com.purplemango.app.grpc.client.TenantGrpcClient;
import com.purplemango.app.model.tenant.AddTenant;
import com.purplemango.app.model.tenant.Tenant;
import com.purplemango.app.model.tenant.UpdateTenant;
import com.purplemango.app.model.tenant.ViewTenant;
import com.purplemango.app.proto.tenant.TenantGrpc;
import com.purplemango.app.service.tenants.TenantService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantController {

    TenantService tenantService;
    TenantGrpcClient tenantGrpcClient;

    @Autowired
    public TenantController(TenantService tenantService, TenantGrpcClient tenantGrpcClient) {
        this.tenantService = tenantService;
        this.tenantGrpcClient = tenantGrpcClient;
    }

    @GetMapping("/all")
    public  ResponseEntity<?> getAllTenants() { return ResponseEntity.ok(tenantService.getAllTenants()); }

    @GetMapping
    public ResponseEntity<?> getAllTenants(@RequestParam(required = true) int page,
                                           @RequestParam(required = true) int size,
                                           @RequestParam(required = true, value = "q") String  sort,
                                           @RequestParam(required = true) Sort.Direction direction) {
        if (page < 0 || size < 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tenantService.getAllTenants(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sort))));
    }

    @GetMapping(value = "/find-by-id", produces = "application/x-protobuf")
    public ResponseEntity<TenantGrpc> getTenantById(@RequestParam("tenant-id") String tenantId) {
//        return tenantGrpcClient.readTenantById(tenantId);
        return ResponseEntity.ok(tenantGrpcClient.readTenantById(tenantId));
    }

    @PostMapping
    public ResponseEntity<ViewTenant> createTenant(@RequestBody @Validated AddTenant entity) {
        ViewTenant tenant = tenantService.createTenant(entity);
        return ResponseEntity.ok(tenant);
    }

    @PutMapping("/{tenant-id}")
    public ResponseEntity<ViewTenant> createOrUpdateTenant(@PathVariable("tenant-id") ObjectId tenantId, @RequestBody @Validated UpdateTenant entity) {
        if (tenantId == null) {
            return ResponseEntity.badRequest().build();
        }
        ViewTenant tenant = tenantService.createOrUpdateTenant(entity, tenantId);
        return ResponseEntity.ok(tenant);
    }
}
