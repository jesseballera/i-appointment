package com.purplemango.app.model.tenant;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Builder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Builder
@Document(collection = "tenants")
public record Tenant(
        @MongoId ObjectId id,
        @Indexed(unique = true) String companyName,
        @Indexed(unique = true) String companyCode) {

    public static Tenant build(AddTenant addTenant) {
        return Tenant.builder()
                .id(ObjectId.get())
                .companyName(addTenant.companyName())
                .companyCode(addTenant.companyCode())
                .build();
    }

    public static Tenant upsert(UpdateTenant entity, ObjectId id) {
        return Tenant.builder()
                .id(id)
                .companyName(entity.companyName())
                .build();
    }
}
