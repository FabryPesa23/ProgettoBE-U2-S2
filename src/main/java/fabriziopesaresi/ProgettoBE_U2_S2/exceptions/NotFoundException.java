package fabriziopesaresi.ProgettoBE_U2_S2.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("Risorsa con id " + id + " non trovata");
    }
}