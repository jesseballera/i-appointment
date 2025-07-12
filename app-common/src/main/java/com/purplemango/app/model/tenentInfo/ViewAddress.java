package com.purplemango.app.model.tenentInfo;

import org.bson.types.ObjectId;

public record ViewAddress(
        ObjectId id,
        String unit,
        String building,
        String street,
        String barangay,
        String city,
        String province,
        Integer zipcode) {

    public static ViewAddress of(Address address) {
        return new ViewAddress(
                address.id(),
                address.unit(),
                address.building(),
                address.street(),
                address.barangay(),
                address.city(),
                address.province(),
                address.zipcode()
        );
    }
}
