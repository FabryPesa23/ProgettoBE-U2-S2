package fabriziopesaresi.ProgettoBE_U2_S2.services;

import fabriziopesaresi.ProgettoBE_U2_S2.entities.Viaggio;
import fabriziopesaresi.ProgettoBE_U2_S2.exceptions.NotFoundException;
import fabriziopesaresi.ProgettoBE_U2_S2.payloads.ViaggioDTO;
import fabriziopesaresi.ProgettoBE_U2_S2.repositories.ViaggioRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ViaggiService {

    private final ViaggioRepository viaggioRepository;

    public ViaggiService(ViaggioRepository viaggioRepository) {
        this.viaggioRepository = viaggioRepository;
    }

    public Viaggio save(ViaggioDTO body) {
        Viaggio nuovo = new Viaggio();
        nuovo.setDestinazione(body.destinazione());
        nuovo.setData(body.data());
        nuovo.setStato(body.stato());
        return this.viaggioRepository.save(nuovo);
    }

    public Viaggio findById(UUID id) {
        return this.viaggioRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Viaggio updateStato(UUID id, String nuovoStato) {
        Viaggio found = this.findById(id);
        found.setStato(nuovoStato);
        return this.viaggioRepository.save(found);
    }
}