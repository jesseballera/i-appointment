package com.purplemango.app.model.tenant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Builder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Builder
public record ViewTenantUser(
        @JsonProperty("tenantUserId")
        @JsonSerialize(using= ToStringSerializer.class) ObjectId id,
        ViewTenant tenant,
        String firstName,
        String lastName) {

    public static ViewTenantUser of(TenantUser tenantUser) {
        return ViewTenantUser.builder()
                .id(tenantUser.id())
//                .tenant(tenant)
                .firstName(tenantUser.firstName())
                .lastName(tenantUser.lastName())
                .build();
    }
}
