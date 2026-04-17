package Raviolz.u2w2d5.payloads;

import java.time.LocalDate;

public record NewTripDTO(
        String destination,
        LocalDate tripDate
) {
}