package fabriziopesaresi.ProgettoBE_U2_S2.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ViaggioDTO(
        @NotEmpty(message = "La destinazione è obbligatoria")
        String destinazione,

        @NotNull(message = "La data è obbligatoria")
        LocalDate data,

        @NotEmpty(message = "Lo stato è obbligatorio (es. IN_PROGRAMMA, COMPLETATO)")
        String stato
) {}