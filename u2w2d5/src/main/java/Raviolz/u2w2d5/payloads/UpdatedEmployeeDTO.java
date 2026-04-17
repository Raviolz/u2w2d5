package Raviolz.u2w2d5.payloads;


public record UpdatedEmployeeDTO(
        String username,
        String name,
        String surname,
        String email
) {
}