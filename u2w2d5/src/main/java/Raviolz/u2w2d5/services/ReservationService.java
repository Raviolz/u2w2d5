package Raviolz.u2w2d5.services;


import Raviolz.u2w2d5.entities.Employee;
import Raviolz.u2w2d5.entities.Reservation;
import Raviolz.u2w2d5.entities.Trip;
import Raviolz.u2w2d5.exceptions.AlreadyExistEx;
import Raviolz.u2w2d5.exceptions.BadRequestEx;
import Raviolz.u2w2d5.exceptions.NotFoundEx;
import Raviolz.u2w2d5.payloads.NewReservationDTO;
import Raviolz.u2w2d5.repositories.ReservationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationRepository rRep;
    private final EmployeeService eServ;
    private final TripService tServ;

    public ReservationService(ReservationRepository rRep, EmployeeService eServ, TripService tServ) {
        this.rRep = rRep;
        this.eServ = eServ;
        this.tServ = tServ;
    }

    public Reservation save(NewReservationDTO body) {

        Employee foundEmployee = eServ.findById(body.employeeId());
        Trip foundTrip = tServ.findById(body.tripId());

        if (rRep.existsByEmployeeAndTrip(foundEmployee, foundTrip)) {
            throw new BadRequestEx("Hai gia' prenotato per questo viaggio");
        }

        if (rRep.existsByEmployeeAndTripDate(foundEmployee, foundTrip.getTripDate())) {
            throw new BadRequestEx("Dipendente gia occupato altrove " + foundTrip.getTripDate());
        }

        Reservation newReservation = new Reservation(
                body.notes(),
                foundEmployee,
                foundTrip
        );

        return rRep.save(newReservation);
    }


    public Page<Reservation> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return rRep.findAll(pageable);
    }

    public Reservation findById(UUID id) {
        return rRep.findById(id)
                .orElseThrow(() -> new NotFoundEx("Prenotazione con id " + id + " non trovata"));
    }


    public Reservation findByIdAndUpdate(UUID id, NewReservationDTO body) {
        Reservation found = this.findById(id);

        Employee foundEmployee = eServ.findById(body.employeeId());
        Trip foundTrip = tServ.findById(body.tripId());

        if (rRep.existsByEmployeeAndTripAndIdNot(foundEmployee, foundTrip, id)) {
            throw new AlreadyExistEx("Dipendente gia'prenotato per questo viaggio");
        }

        if (rRep.existsByEmployeeAndTripDateAndIdNot(foundEmployee, foundTrip.getTripDate(), id)) {
            throw new AlreadyExistEx("Dipendente gia occupato in data: " + foundTrip.getTripDate());
        }

        found.setNotes(body.notes());
        found.setEmployee(foundEmployee);
        found.setTrip(foundTrip);

        return rRep.save(found);
    }


    public void findByIdAndDelete(UUID id) {
        Reservation found = this.findById(id);
        rRep.delete(found);
    }
}

