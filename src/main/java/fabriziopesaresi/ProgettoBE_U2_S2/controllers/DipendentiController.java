package fabriziopesaresi.ProgettoBE_U2_S2.controllers;

import fabriziopesaresi.ProgettoBE_U2_S2.entities.Dipendente;
import fabriziopesaresi.ProgettoBE_U2_S2.exceptions.ValidationException;
import fabriziopesaresi.ProgettoBE_U2_S2.payloads.DipendenteDTO;
import fabriziopesaresi.ProgettoBE_U2_S2.payloads.NewUserRespDTO;
import fabriziopesaresi.ProgettoBE_U2_S2.services.DipendentiService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {

    private final DipendentiService dipendentiService;

    public DipendentiController(DipendentiService dipendentiService) {
        this.dipendentiService = dipendentiService;
    }

    @GetMapping
    public Page<Dipendente> getAll(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String sortBy) {
        return this.dipendentiService.findAll(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserRespDTO save(@RequestBody @Validated DipendenteDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new ValidationException(validation.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage()).collect(Collectors.toList()));
        }
        return new NewUserRespDTO(this.dipendentiService.save(body).getId());
    }

    @GetMapping("/{id}")
    public Dipendente findById(@PathVariable UUID id) {
        return this.dipendentiService.findById(id);
    }
}
