package com.purplemango.app.model.tenentInfo;

import com.purplemango.app.model.accommodation.AccommodationType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

public record ViewInstitution(
        ObjectId id,
        String companyName,
        String companyCode,
        String permitType,
        Address address,
        AccommodationType accommodationType) {
}
