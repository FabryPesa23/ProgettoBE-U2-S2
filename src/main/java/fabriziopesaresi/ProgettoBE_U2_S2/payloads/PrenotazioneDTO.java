package fabriziopesaresi.ProgettoBE_U2_S2.payloads;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record PrenotazioneDTO(
        @NotNull(message = "L'ID del dipendente è obbligatorio")
        UUID dipendenteId,

        @NotNull(message = "L'ID del viaggio è obbligatorio")
        UUID viaggioId,

        String note
) {}