package Raviolz.u2w2d5.payloads;


import Raviolz.u2w2d5.entities.TripState;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdatedTripDTO(
        String destination,
        @NotNull(message = "La data del viaggio è obbligatoria")
        @FutureOrPresent(message = "La data del viaggio non può essere nel passato")
        LocalDate tripDate,
        TripState tripState
) {
}