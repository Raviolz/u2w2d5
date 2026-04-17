package Raviolz.u2w2d5.entities;

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
    @Column(name = "application_date", nullable = false)
    private LocalDate applicationDate;
    @Column(name = "notes", length = 200)
    private String notes;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    public Reservation(String notes, Employee employee, Trip trip) {
        this.applicationDate = LocalDate.now();
        this.notes = notes;
        this.employee = employee;
        this.trip = trip;
    }
    
}


