package fabriziopesaresi.ProgettoBE_U2_S2.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}