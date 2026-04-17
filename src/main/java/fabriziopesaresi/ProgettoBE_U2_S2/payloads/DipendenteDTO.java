package fabriziopesaresi.ProgettoBE_U2_S2.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record DipendenteDTO(
        @NotEmpty(message = "Username obbligatorio") String username,
        @NotEmpty(message = "Nome obbligatorio") String nome,
        @NotEmpty(message = "Cognome obbligatorio") String cognome,
        @Email(message = "Email non valida") @NotEmpty(message = "Email obbligatoria") String email
) {}