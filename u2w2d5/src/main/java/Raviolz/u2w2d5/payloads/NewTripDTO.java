package Raviolz.u2w2d5.payloads;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewTripDTO(
        @NotBlank(message = "La destinazione e' obbligatoria")
        String destination,
        @NotNull(message = "La data del viaggio e' obbligatoria")
        @FutureOrPresent(message = "Troppo tardi!")
        LocalDate tripDate
) {
}