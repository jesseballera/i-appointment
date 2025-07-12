package com.purplemango.app.model.tenentInfo;

import org.bson.types.ObjectId;

public record AddAddress(
        String unit,
        String building,
        String street,
        String barangay,
        String city,
        String province,
        Integer zipcode) { }
