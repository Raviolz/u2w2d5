package Raviolz.u2w2d5.repositories;

import Raviolz.u2w2d5.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
}
