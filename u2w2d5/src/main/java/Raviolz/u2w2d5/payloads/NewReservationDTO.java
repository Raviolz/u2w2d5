package Raviolz.u2w2d5.payloads;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record NewReservationDTO(
        String notes,
        @NotBlank(message = "Id dipendente obbligatorio")
        UUID employeeId,
        @NotBlank(message = "Id del viaggio  obbligatorio")
        UUID tripId
) {
}