package com.purplemango.app.model.tenantInfo;

public record AddAddress(
        String unit,
        String building,
        String street,
        String barangay,
        String city,
        String province,
        Integer zipcode) { }
