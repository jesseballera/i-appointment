package com.purplemango.app.model.accommodation;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "accommodation_types")
public record AccommodationType(
        @MongoId ObjectId id,
        @Indexed(unique = true) String name) {
    public static AccommodationType of(AddAccommodationType addAccommodationType) {
        return new AccommodationType(
                ObjectId.get(),
                addAccommodationType.name()
        );
    }

    public static AccommodationType of(ViewAccommodationType viewAccommodationType) {
        return new AccommodationType(
                viewAccommodationType.id(),
                viewAccommodationType.name()
        );
    }
}
