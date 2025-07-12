package com.purplemango.app.repository.accommodations;

import com.purplemango.app.model.accommodation.AccommodationType;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccommodationTypeRepository {
    AccommodationType save(AccommodationType entity);
    List<AccommodationType> findAll();
    Page<AccommodationType> findAll(Pageable pageable);
    Optional<AccommodationType> findById(ObjectId entityId);
    Optional<AccommodationType> findByName(String name);
    void deleteById(ObjectId id);
}
