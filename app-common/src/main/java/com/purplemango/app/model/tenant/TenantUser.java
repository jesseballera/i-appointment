package com.purplemango.app.model.tenant;

import lombok.Builder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Builder
@Document(collection = "users")
public record TenantUser(
        @MongoId ObjectId id,
        Tenant tenant,
        String firstName,
        String lastName) {

    public static TenantUser build(AddTenantUser addTenantUser, Tenant tenant) {
        return TenantUser.builder()
                .id(ObjectId.get())
                .tenant(tenant)
                .firstName(addTenantUser.firstName())
                .lastName(addTenantUser.lastName())
                .build();
    }
}
