package com.purplemango.app.model.accommodation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;

public record ViewAccommodationType(
        @JsonSerialize(using= ToStringSerializer.class) ObjectId id,
        String name) {
    public static ViewAccommodationType of(AccommodationType accommodationType) {
        return new ViewAccommodationType(accommodationType.id(), accommodationType.name());
    }
}
