package fabriziopesaresi.ProgettoBE_U2_S2.services;

import fabriziopesaresi.ProgettoBE_U2_S2.entities.Dipendente;
import fabriziopesaresi.ProgettoBE_U2_S2.exceptions.BadRequestException;
import fabriziopesaresi.ProgettoBE_U2_S2.exceptions.NotFoundException;
import fabriziopesaresi.ProgettoBE_U2_S2.payloads.DipendenteDTO;
import fabriziopesaresi.ProgettoBE_U2_S2.repositories.DipendenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class DipendentiService {

    private final DipendenteRepository dipendenteRepository;

    public DipendentiService(DipendenteRepository dipendenteRepository) {
        this.dipendenteRepository = dipendenteRepository;
    }

    public Dipendente save(DipendenteDTO body) {
        if (this.dipendenteRepository.existsByEmail(body.email()))
            throw new BadRequestException("L'email " + body.email() + " è già in uso!");

        Dipendente nuovo = new Dipendente(body.username(), body.nome(), body.cognome(), body.email());
        nuovo.setAvatar("https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome());

        Dipendente saved = this.dipendenteRepository.save(nuovo);
        log.info("Dipendente salvato con id: " + saved.getId());
        return saved;
    }

    public Page<Dipendente> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.dipendenteRepository.findAll(pageable);
    }

    public Dipendente findById(UUID id) {
        return this.dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
