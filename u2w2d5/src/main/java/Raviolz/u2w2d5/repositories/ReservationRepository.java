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

    // per update per non trovare se stessa nel db ...  <> diverso da
    @Query("""  
            SELECT COUNT(r) > 0
            FROM Reservation r
            WHERE r.employee = :employee
            AND r.trip = :trip
            AND r.id <> :reservationId
            """)
    boolean existsByEmployeeAndTripAndIdNot(@Param("employee") Employee employee,
                                            @Param("trip") Trip trip,
                                            @Param("reservationId") UUID reservationId);

    @Query("""
            SELECT COUNT(r) > 0
            FROM Reservation r
            WHERE r.employee = :employee
            AND r.trip.tripDate = :tripDate
            AND r.id <> :reservationId
            """)
    boolean existsByEmployeeAndTripDateAndIdNot(@Param("employee") Employee employee,
                                                @Param("tripDate") LocalDate tripDate,
                                                @Param("reservationId") UUID reservationId);
}




