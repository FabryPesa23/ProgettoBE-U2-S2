package fabriziopesaresi.ProgettoBE_U2_S2.controllers;

import fabriziopesaresi.ProgettoBE_U2_S2.entities.Viaggio;
import fabriziopesaresi.ProgettoBE_U2_S2.exceptions.ValidationException;
import fabriziopesaresi.ProgettoBE_U2_S2.payloads.ViaggioDTO;
import fabriziopesaresi.ProgettoBE_U2_S2.services.ViaggiService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/viaggi")
public class ViaggiController {

    private final ViaggiService viaggiService;

    public ViaggiController(ViaggiService viaggiService) {
        this.viaggiService = viaggiService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio save(@RequestBody @Validated ViaggioDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new ValidationException(validation.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage()).collect(Collectors.toList()));
        }
        return this.viaggiService.save(body);
    }

    @PatchMapping("/{id}/stato")
    public Viaggio updateStato(@PathVariable UUID id, @RequestBody String nuovoStato) {
        // Rimuove eventuali virgolette se passate come stringa semplice nel body
        String statoLimpio = nuovoStato.replace("\"", "");
        return this.viaggiService.updateStato(id, statoLimpio);
    }
}