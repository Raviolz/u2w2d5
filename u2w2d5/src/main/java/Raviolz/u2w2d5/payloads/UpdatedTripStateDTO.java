package Raviolz.u2w2d5.payloads;


import Raviolz.u2w2d5.entities.TripState;
import jakarta.validation.constraints.NotNull;

public record UpdatedTripStateDTO(
        @NotNull(message = "Lo stato del viaggio è obbligatorio")
        TripState tripState
) {
}