package com.purplemango.app.service.accommodations;

import com.purplemango.app.exceptions.DuplicateEntryException;
import com.purplemango.app.model.accommodation.AccommodationType;
import com.purplemango.app.model.accommodation.AddAccommodationType;
import com.purplemango.app.model.accommodation.ViewAccommodationType;
import com.purplemango.app.repository.accommodations.AccommodationTypeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccommodationTypeServiceImpl implements AccommodationTypeService {
    private final AccommodationTypeRepository repository;
    private final AccommodationTypeRepository accommodationTypeRepository;

    @Autowired
    public AccommodationTypeServiceImpl(AccommodationTypeRepository repository, AccommodationTypeRepository accommodationTypeRepository) {
        this.repository = repository;
        this.accommodationTypeRepository = accommodationTypeRepository;
    }

    @Override
    public ViewAccommodationType create(AddAccommodationType entity) {
        if (accommodationTypeRepository.findByName(entity.name()).isPresent())
            throw new DuplicateEntryException("Accommodation type already exists");
        return ViewAccommodationType.of(accommodationTypeRepository.save(AccommodationType.of(entity)));
    }

    @Override
    public List<ViewAccommodationType> viewAll() {
        return accommodationTypeRepository.findAll()
                .stream()
                .map(ViewAccommodationType::of)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ViewAccommodationType> viewAll(Pageable pageable) {
        return accommodationTypeRepository.findAll(pageable)
                .map(ViewAccommodationType::of);
    }

    @Override
    public ViewAccommodationType viewById(ObjectId entityId) {
        return ViewAccommodationType
                .of(accommodationTypeRepository.findById(entityId).orElseThrow(() -> new RuntimeException("Accommodation type not found")));
    }

    @Override
    public ViewAccommodationType viewByName(String name) {
        return ViewAccommodationType
                .of(accommodationTypeRepository.findByName(name).orElseThrow(() -> new RuntimeException("Accommodation type not found")));
    }

    @Override
    public void removeById(ObjectId id) {
        accommodationTypeRepository.deleteById(id);
    }
}
