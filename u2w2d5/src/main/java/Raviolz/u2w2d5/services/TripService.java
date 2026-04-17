package Raviolz.u2w2d5.services;

import Raviolz.u2w2d5.entities.Trip;
import Raviolz.u2w2d5.exceptions.AlreadyExistEx;
import Raviolz.u2w2d5.exceptions.NotFoundEx;
import Raviolz.u2w2d5.payloads.NewTripDTO;
import Raviolz.u2w2d5.payloads.UpdatedTripDTO;
import Raviolz.u2w2d5.repositories.TripRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public Page<Trip> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return tRep.findAll(pageable);
    }

    public Trip findById(UUID id) {
        return tRep.findById(id)
                .orElseThrow(() -> new NotFoundEx("Viaggio con id " + id + " non trovato"));
    }


    public Trip findByIdAndUpdate(UUID id, UpdatedTripDTO body) {
        Trip found = this.findById(id);

        found.setDestination(body.destination());
        found.setTripDate(body.tripDate());
        return tRep.save(found);
    }

    public void findByIdAndDelete(UUID id) {
        Trip found = this.findById(id);
        tRep.delete(found);
    }
}
