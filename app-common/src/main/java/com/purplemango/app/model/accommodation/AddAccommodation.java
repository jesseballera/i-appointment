package com.purplemango.app.model.accommodation;

import com.purplemango.app.model.tenentInfo.AddAddress;
import com.purplemango.app.model.tenentInfo.Address;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document(collection = "accommodations")
public record AddAccommodation(
        AddAddress address1,
        AddAddress address2,
        String accommodationType) {
}


