package com.purplemango.app.model.tenantInfo;

import com.purplemango.app.model.accommodation.AccommodationType;
import org.bson.types.ObjectId;

public record ViewInstitution(
        ObjectId id,
        String companyName,
        String companyCode,
        String permitType,
        Address address,
        AccommodationType accommodationType) {
}
