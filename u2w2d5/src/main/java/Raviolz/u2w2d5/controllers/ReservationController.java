package Raviolz.u2w2d5.controllers;

import Raviolz.u2w2d5.entities.Reservation;
import Raviolz.u2w2d5.payloads.NewReservationDTO;
import Raviolz.u2w2d5.services.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService rServ;

    public ReservationController(ReservationService rServ) {
        this.rServ = rServ;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody NewReservationDTO body) {
        return rServ.save(body);
    }

    @GetMapping
    public Page<Reservation> findAll(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(defaultValue = "requestDate") String sortBy) {
        return rServ.findAll(page, size, sortBy);
    }


    @PutMapping("/{id}")
    public Reservation findByIdAndUpdate(@PathVariable UUID id, @RequestBody NewReservationDTO body) {
        return rServ.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id) {
        rServ.findByIdAndDelete(id);
    }
}

