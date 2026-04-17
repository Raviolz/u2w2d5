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

@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(nullable = false, length = 30)
    private String destination;
    @Column(nullable = false)
    private LocalDate tripDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TripState tripState;

    public Trip(String destination, LocalDate date) {
        this.destination = destination;
        this.tripDate = date;
        this.tripState = TripState.SCHEDULED;
    }

}
