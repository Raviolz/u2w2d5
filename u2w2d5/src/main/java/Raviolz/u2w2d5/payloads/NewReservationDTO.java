package Raviolz.u2w2d5.payloads;

import java.util.UUID;

public record NewReservationDTO(
        String notes,
        UUID employeeId,
        UUID tripId
) {
}