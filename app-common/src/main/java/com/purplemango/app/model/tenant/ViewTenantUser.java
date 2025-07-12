package com.purplemango.app.model.tenant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;

public record ViewTenantUser(
        @JsonProperty("tenantUserId")
        @JsonSerialize(using= ToStringSerializer.class) ObjectId id,
        ViewTenant tenant,
        String tenantCode,
        String firstName,
        String lastName) {

    public static ViewTenantUser of(TenantUser tenantUser) {
        return new ViewTenantUser(
                tenantUser.id(),
                ViewTenant.of(tenantUser.tenant()),
                tenantUser.tenantCode(),
                tenantUser.firstName(),
                tenantUser.lastName()
        );
    }
}
