package Raviolz.u2w2d5.repositories;

import Raviolz.u2w2d5.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {

    boolean existsByDestinationAndTripDate(String destination, LocalDate tripDate);
}
