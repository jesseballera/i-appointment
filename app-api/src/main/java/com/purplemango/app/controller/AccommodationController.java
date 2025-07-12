package com.purplemango.app.controller;

import com.purplemango.app.annotations.ValidEmail;
import com.purplemango.app.model.accommodation.AddAccommodation;
import com.purplemango.app.model.accommodation.ViewAccommodation;
import com.purplemango.app.service.accommodations.AccommodationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accommodations")
public class AccommodationController {
    private final AccommodationService accommodationService;

    @Autowired
    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    // get all accommodations
    @GetMapping("/{tenant}")
    public ResponseEntity<List<ViewAccommodation>> getAllAccommodations(@PathVariable String tenant) {
        return ResponseEntity.ok(accommodationService.viewAll(tenant));
    }

    // save new accommodation
    @PostMapping("/{tenant}")
    public ResponseEntity<ViewAccommodation> createAccommodation(@PathVariable String tenant, @RequestBody @Valid AddAccommodation addAccommodation) {
        return ResponseEntity.ok(accommodationService.create(addAccommodation, tenant));
    }
}
