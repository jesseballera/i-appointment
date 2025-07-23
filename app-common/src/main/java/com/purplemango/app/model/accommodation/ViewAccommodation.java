package com.purplemango.app.model.accommodation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.purplemango.app.model.tenantInfo.ViewAddress;
import org.bson.types.ObjectId;


public record ViewAccommodation(
        @JsonSerialize(using= ToStringSerializer.class) ObjectId id,
        String tenantCode,
        ViewAddress address1,
        ViewAddress address2,
        ViewAccommodationType accommodationType) {

        public static ViewAccommodation of(Accommodation accommodation) {
            return new ViewAccommodation(
                    accommodation.id(),
                    accommodation.tenantCode(),
                    ViewAddress.of(accommodation.address1()),
                    ViewAddress.of(accommodation.address2()),
                    ViewAccommodationType.of(accommodation.accommodationType())
            );
        }
}


