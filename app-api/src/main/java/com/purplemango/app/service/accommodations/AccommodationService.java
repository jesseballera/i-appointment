package com.purplemango.app.service.accommodations;

import com.purplemango.app.model.accommodation.AddAccommodation;
import com.purplemango.app.model.accommodation.ViewAccommodation;

import java.util.List;

public interface AccommodationService {
    ViewAccommodation create(AddAccommodation entity, String tenantCode);
    List<ViewAccommodation> viewAll(String tenantCode);
}
