package Raviolz.u2w2d5.payloads;

import java.util.UUID;

public record UpdatedReservationDTO(
        String notes,
        UUID employeeId,
        UUID tripId
) {
}