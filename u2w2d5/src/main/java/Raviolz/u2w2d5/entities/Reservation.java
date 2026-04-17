package Raviolz.u2w2d5.entities;

import Raviolz.u2w2d5.exceptions.ValidationEx;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString


@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(name = "request_date", nullable = false)
    private LocalDate requestDate; // RICORDO non data del viaggio.. faccio controllo poi su tripdate
    @Column(name = "notes", length = 200)
    private String notes;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    public Reservation(String notes, Employee employee, Trip trip) {
        if (employee == null) {
            throw new ValidationEx("Per chi prenotiamo?");
        }
        if (trip == null) {
            throw new ValidationEx("Per dove?");
        }
        if (trip.getTripDate().isBefore(LocalDate.now())) {
            throw new ValidationEx("Troppo tardi per prenotarsi!");
        }
        if (trip.getTripState() == TripState.CANCELLED) {
            throw new ValidationEx("Non puoi prenotare un viaggio annullato");
        }

        if (trip.getTripState() == TripState.COMPLETED) {
            throw new ValidationEx("Non puoi prenotare un viaggio gia' completato");
        }


        this.requestDate = LocalDate.now();
        this.notes = notes;
        this.employee = employee;
        this.trip = trip;
    }
// TODO fare controlli nei setter se ho tempo
}


