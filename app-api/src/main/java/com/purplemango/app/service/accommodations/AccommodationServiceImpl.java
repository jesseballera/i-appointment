package com.purplemango.app.service.accommodations;

import com.purplemango.app.model.accommodation.*;
import com.purplemango.app.repository.accommodations.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final AccommodationTypeService accommodationTypeService;

    @Autowired
    public AccommodationServiceImpl(AccommodationRepository accommodationRepository,
                                    AccommodationTypeService accommodationTypeService) {
        this.accommodationRepository = accommodationRepository;
        this.accommodationTypeService = accommodationTypeService;
    }

    @Override
    public ViewAccommodation create(AddAccommodation entity, String tenantCode) {
        ViewAccommodationType accommodationType = accommodationTypeService.viewByName(entity.accommodationType());
        return ViewAccommodation
                .of(accommodationRepository.save(
                        Accommodation.of(
                                entity.address1(),
                                entity.address2(),
                                tenantCode,
                                AccommodationType.of(accommodationType))
                        )
                );
    }

    @Override
    public List<ViewAccommodation> viewAll(String tenantCode) {
        return accommodationRepository.findAll(tenantCode)
                .stream()
                .map(ViewAccommodation::of)
                .collect(Collectors.toList());
    }
}
