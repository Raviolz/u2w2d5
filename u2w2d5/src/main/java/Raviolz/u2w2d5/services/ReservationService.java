package Raviolz.u2w2d5.services;


import Raviolz.u2w2d5.entities.Employee;
import Raviolz.u2w2d5.entities.Reservation;
import Raviolz.u2w2d5.entities.Trip;
import Raviolz.u2w2d5.exceptions.BadRequestEx;
import Raviolz.u2w2d5.payloads.NewReservationDTO;
import Raviolz.u2w2d5.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

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
}

