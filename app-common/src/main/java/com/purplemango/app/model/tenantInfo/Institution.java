package com.purplemango.app.model.tenantInfo;

import com.purplemango.app.model.accommodation.AccommodationType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "institutions")
public record Institution(
        @MongoId ObjectId id,
        String companyName,
        String companyCode,
        String permitType,
        Address address,
        AccommodationType accommodationType) {
}
