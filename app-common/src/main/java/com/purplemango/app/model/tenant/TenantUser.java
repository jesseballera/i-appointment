package com.purplemango.app.model.tenant;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "users")
public record TenantUser(
        @MongoId ObjectId id,
        String tenantCode,
        Tenant tenant,
        String firstName,
        String lastName) {

    public static TenantUser build(AddTenantUser addTenantUser, Tenant tenant) {
        return new TenantUser(
                ObjectId.get(),
                tenant.companyCode(),
                tenant,
                addTenantUser.firstName(),
                addTenantUser.lastName());
    }
}
