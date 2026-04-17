package Raviolz.u2w2d5.controllers;

import Raviolz.u2w2d5.entities.Reservation;
import Raviolz.u2w2d5.payloads.NewReservationDTO;
import Raviolz.u2w2d5.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}