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

@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(nullable = false, length = 100)
    private String destination;
    @Column(nullable = false)
    private LocalDate tripDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TripState tripState;

    public Trip(String destination, LocalDate date) {
        if (destination == null || destination.isBlank()) {
            throw new ValidationEx("Destinazione obbligatoria");
        }
        if (date == null) {
            throw new ValidationEx("Data del viaggio obbligatoria");
        }
        if (date.isBefore(LocalDate.now())) {
            throw new ValidationEx("La data non puo' gia' esser passata");
        }
        this.destination = destination;
        this.tripDate = date;
        this.tripState = TripState.SCHEDULED;
    }
// TODO fare controlli nei setter se ho tempo
}
