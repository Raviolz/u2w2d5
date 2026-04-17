package Raviolz.u2w2d5.controllers;

import Raviolz.u2w2d5.entities.Trip;
import Raviolz.u2w2d5.payloads.NewTripDTO;
import Raviolz.u2w2d5.services.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}