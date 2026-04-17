package Raviolz.u2w2d5.repositories;

import Raviolz.u2w2d5.entities.Employee;
import Raviolz.u2w2d5.entities.Reservation;
import Raviolz.u2w2d5.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    boolean existsByEmployeeAndTrip(Employee employee, Trip trip);

    @Query("""
            SELECT COUNT(r) > 0
            FROM Reservation r
            WHERE r.employee = :employee
            AND r.trip.tripDate = :tripDate
            """)
    boolean existsByEmployeeAndTripDate(@Param("employee") Employee employee,
                                        @Param("tripDate") LocalDate tripDate);
}

