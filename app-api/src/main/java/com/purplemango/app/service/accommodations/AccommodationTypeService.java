package com.purplemango.app.service.accommodations;

import com.purplemango.app.model.accommodation.AccommodationType;
import com.purplemango.app.model.accommodation.AddAccommodationType;
import com.purplemango.app.model.accommodation.ViewAccommodationType;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccommodationTypeService {
    ViewAccommodationType create(AddAccommodationType entity);
    List<ViewAccommodationType> viewAll();
    Page<ViewAccommodationType> viewAll(Pageable pageable);
    ViewAccommodationType viewById(ObjectId entityId);
    ViewAccommodationType viewByName(String name);
    void removeById(ObjectId id);
}
