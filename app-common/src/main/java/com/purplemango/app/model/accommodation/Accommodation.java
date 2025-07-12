package com.purplemango.app.model.accommodation;

import com.purplemango.app.model.tenentInfo.AddAddress;
import com.purplemango.app.model.tenentInfo.Address;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document(collection = "accommodations")
public record Accommodation(
        @MongoId ObjectId id,
        String tenantCode,
        Address address1,
        Address address2,
        AccommodationType accommodationType) {
    public static Accommodation of(AddAddress addAddress1, AddAddress addAddress2,
                                   String tenantCode, AccommodationType accommodationType) {
        return new Accommodation(
                ObjectId.get(),
                tenantCode,
                Address.of(addAddress1),
                Address.of(addAddress2),
                accommodationType
        );
    }
}


