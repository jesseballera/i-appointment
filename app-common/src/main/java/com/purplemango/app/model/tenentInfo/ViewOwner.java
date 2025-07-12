package com.purplemango.app.model.tenentInfo;

import com.purplemango.app.model.accommodation.Accommodation;
import com.purplemango.app.model.tenant.Tenant;
import com.purplemango.app.model.tenant.ViewTenant;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

public record ViewOwner(
        ObjectId id,
        String tenantCode,
        ViewTenant tenant,
        String firstName,
        String lastName,
        String middleName,
        Institution institution,
        Accommodation accommodation,
        List<Contact> contacts) {

    public record Contact(
            @MongoId ObjectId id,
            @Indexed(unique = true) String mobileNumber,
            @Indexed(unique = true) String telephoneNumber,
            @Indexed(unique = true) String email) { }

}
