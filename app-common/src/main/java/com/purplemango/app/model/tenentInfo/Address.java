package com.purplemango.app.model.tenentInfo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

public record Address(
        @MongoId ObjectId id,
        String unit,
        String building,
        String street,
        String barangay,
        String city,
        String province,
        Integer zipcode) {

    public static Address of(AddAddress addAddress) {
        return new Address(
                ObjectId.get(),
                addAddress.unit(),
                addAddress.building(),
                addAddress.street(),
                addAddress.barangay(),
                addAddress.city(),
                addAddress.province(),
                addAddress.zipcode()
        );
    }
}
