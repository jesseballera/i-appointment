package com.purplemango.app.model.accommodation;

import com.purplemango.app.model.tenantInfo.AddAddress;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "accommodations")
public record AddAccommodation(
        @NotNull AddAddress address1,
        AddAddress address2,
        @NotNull String accommodationType) {
}


