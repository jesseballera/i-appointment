package com.purplemango.app.model.tenant;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "tenants")
public record Tenant(
        @MongoId ObjectId id,
        @Indexed(unique = true) String companyName,
        @Indexed(unique = true) String companyCode) {

    public static Tenant build(AddTenant addTenant) {
        return new Tenant(ObjectId.get(),
                addTenant.companyName(),
                addTenant.companyCode()
        );
    }

    public static Tenant upsert(UpdateTenant entity, ObjectId id) {
        return new Tenant(ObjectId.get(),
                entity.companyName(),
                entity.companyName()
        );
    }
}
