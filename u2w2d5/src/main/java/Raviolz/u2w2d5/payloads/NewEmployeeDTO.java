package Raviolz.u2w2d5.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record NewEmployeeDTO(

        @NotBlank(message = "Username obbligatorio")
        String username,
        @NotBlank(message = "Nome obbligatorio")
        String name,
        @NotBlank(message = "Cognome obbligatorio")
        String surname,
        @NotBlank(message = "Email obbligatoria")
        @Email(message = "Email non valida")
        String email,
        @NotBlank
        String password
) {
}
