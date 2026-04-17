package Raviolz.u2w2d5.services;

import Raviolz.u2w2d5.entities.Trip;
import Raviolz.u2w2d5.exceptions.AlreadyExistEx;
import Raviolz.u2w2d5.payloads.NewTripDTO;
import Raviolz.u2w2d5.repositories.TripRepository;
import org.springframework.stereotype.Service;

@Service
public class TripService {

    private final TripRepository tRep;

    public TripService(TripRepository tRep) {
        this.tRep = tRep;
    }

    public Trip save(NewTripDTO body) {

        if (tRep.existsByDestinationAndTripDate(body.destination(), body.tripDate())) {
            throw new AlreadyExistEx("Esiste gia' un viaggio per " + body.destination() + " in data " + body.tripDate());
        }
        Trip newTrip = new Trip(
                body.destination(),
                body.tripDate()
        );

        return tRep.save(newTrip);
    }

}
