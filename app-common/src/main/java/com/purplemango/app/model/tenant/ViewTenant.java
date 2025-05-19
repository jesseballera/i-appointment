package com.purplemango.app.model.tenant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Builder;
import org.bson.types.ObjectId;
@Builder
public record ViewTenant(
        @JsonProperty("tenantId")
        @JsonSerialize(using= ToStringSerializer.class) ObjectId id,
        @JsonProperty("tenantName") String companyName,
        @JsonProperty("tenantCode") String companyCode) {
         public static ViewTenant of(Tenant tenant) {
            return ViewTenant.builder()
                    .id(tenant.id())
                    .companyName(tenant.companyName())
                    .companyCode(tenant.companyCode())
                    .build();
        }
}
