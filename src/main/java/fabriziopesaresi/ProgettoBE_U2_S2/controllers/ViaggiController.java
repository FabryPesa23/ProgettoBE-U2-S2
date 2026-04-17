package fabriziopesaresi.ProgettoBE_U2_S2.controllers;

import fabriziopesaresi.ProgettoBE_U2_S2.entities.Viaggio;
import fabriziopesaresi.ProgettoBE_U2_S2.exceptions.ValidationException;
import fabriziopesaresi.ProgettoBE_U2_S2.payloads.ViaggioDTO;
import fabriziopesaresi.ProgettoBE_U2_S2.services.ViaggiService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/viaggi")
public class ViaggiController {

    private final ViaggiService viaggiService;

    public ViaggiController(ViaggiService viaggiService) {
        this.viaggiService = viaggiService;
    }

    @GetMapping
    public List<Viaggio> getAllViaggi() {
        return this.viaggiService.findAll();
    }

    @GetMapping("/{id}")
    public Viaggio getViaggioById(@PathVariable UUID id) {
        return this.viaggiService.findById(id);
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

    @PutMapping("/{id}")
    public Viaggio updateViaggio(@PathVariable UUID id, @RequestBody @Validated ViaggioDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new ValidationException(validation.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage()).collect(Collectors.toList()));
        }
        return this.viaggiService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViaggio(@PathVariable UUID id) {
        this.viaggiService.findByIdAndDelete(id);
    }

    // MODIFICATO: Ora usa StatoDTO invece di Map per una pulizia totale del JSON
    @PatchMapping("/{id}/stato")
    public Viaggio updateStato(@PathVariable UUID id, @RequestBody StatoDTO body) {
        return viaggiService.updateStato(id, body.getStato());
    }
}

// CLASSE DI SUPPORTO (Aggiunta qui sotto)
class StatoDTO {
    private String stato;

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }
}