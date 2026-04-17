package fabriziopesaresi.ProgettoBE_U2_S2.exceptions;

import lombok.Getter;
import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private List<String> errors;

    public ValidationException(List<String> errors) {
        super("Errore di validazione nel payload");
        this.errors = errors;
    }
}
