package com.purplemango.app.model.accommodation;

import com.purplemango.app.model.tenantInfo.AddAddress;
import com.purplemango.app.model.tenantInfo.Address;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document(collection = "accommodations")
public record Accommodation(
        @MongoId ObjectId id,
        @NotNull String tenantCode,
        @NotNull Address address1,
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


