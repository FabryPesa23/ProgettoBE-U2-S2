package fabriziopesaresi.ProgettoBE_U2_S2.controllers;

import fabriziopesaresi.ProgettoBE_U2_S2.entities.Prenotazione;
import fabriziopesaresi.ProgettoBE_U2_S2.exceptions.ValidationException;
import fabriziopesaresi.ProgettoBE_U2_S2.payloads.PrenotazioneDTO;
import fabriziopesaresi.ProgettoBE_U2_S2.services.PrenotazioniService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {

    private final PrenotazioniService prenotazioniService;

    public PrenotazioniController(PrenotazioniService prenotazioniService) {
        this.prenotazioniService = prenotazioniService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione save(@RequestBody @Validated PrenotazioneDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new ValidationException(validation.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage()).collect(Collectors.toList()));
        }
        return this.prenotazioniService.save(body);
    }
}