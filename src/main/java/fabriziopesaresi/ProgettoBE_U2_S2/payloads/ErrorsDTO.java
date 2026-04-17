package fabriziopesaresi.ProgettoBE_U2_S2.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorsDTO {
    private String message;
    private LocalDateTime timestamp;
}