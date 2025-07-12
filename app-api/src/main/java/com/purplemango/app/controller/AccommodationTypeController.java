package com.purplemango.app.controller;

import com.purplemango.app.model.accommodation.AddAccommodationType;
import com.purplemango.app.service.accommodations.AccommodationTypeService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accommodation-types")
public class AccommodationTypeController {
    private final AccommodationTypeService accommodationTypeService;

    @Autowired
    public AccommodationTypeController(AccommodationTypeService accommodationTypeService) {
        this.accommodationTypeService = accommodationTypeService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAccommodationTypes() {
        return ResponseEntity.ok(accommodationTypeService.viewAll());
    }
    @GetMapping
    public ResponseEntity<?> getAllAccommodationTypes(@RequestParam(required = true) int page,
                                                      @RequestParam(required = true) int size,
                                                      @RequestParam(required = true, value = "q") String  sort,
                                                      @RequestParam(required = true) Sort.Direction direction) {
        if (page < 0 || size < 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(accommodationTypeService.viewAll(PageRequest.of(page, size, Sort.by(direction, sort))));
    }
    // create accommodation type
    @PostMapping
    public ResponseEntity<?> createAccommodationType(@RequestBody @Valid AddAccommodationType addAccommodationType) {
        return ResponseEntity.ok(accommodationTypeService.create(addAccommodationType));
    }
    //view accommodation type by name
    @GetMapping("/name")
    public ResponseEntity<?> getAccommodationTypeByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(accommodationTypeService.viewByName(name));
    }
    // view accommodation type by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getAccommodationTypeById(@PathVariable("id") String id) {
        return ResponseEntity.ok(accommodationTypeService.viewById(new ObjectId(id)));
    }
    //delete accommodation type by id
    @DeleteMapping("/{id}")
    public void deleteAccommodationTypeById(@PathVariable("id") String id) {
        accommodationTypeService.removeById(new ObjectId(id));
    }
}
