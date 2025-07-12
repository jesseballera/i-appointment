package com.purplemango.app.repository.accommodations;

import com.purplemango.app.model.accommodation.Accommodation;

import java.util.List;

public interface AccommodationRepository {
    Accommodation save(Accommodation accommodation);
    List<Accommodation> findAll(String tenantCode);

}
