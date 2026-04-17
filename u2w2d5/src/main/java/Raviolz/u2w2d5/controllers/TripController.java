package Raviolz.u2w2d5.controllers;

import Raviolz.u2w2d5.entities.Trip;
import Raviolz.u2w2d5.payloads.NewTripDTO;
import Raviolz.u2w2d5.services.TripService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/trips")
public class TripController {

    private final TripService tService;

    public TripController(TripService tService) {
        this.tService = tService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trip save(@RequestBody NewTripDTO body) {
        return tService.save(body);
    }

    @GetMapping
    public Page<Trip> findAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size,
                              @RequestParam(defaultValue = "tripDate") String sortBy) {
        return tService.findAll(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Trip findById(@PathVariable UUID id) {
        return tService.findById(id);
    }


    @PutMapping("/{id}")
    public Trip findByIdAndUpdate(@PathVariable UUID id, @RequestBody NewTripDTO body) {
        return tService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id) {
        tService.findByIdAndDelete(id);
    }
}