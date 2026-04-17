package fabriziopesaresi.ProgettoBE_U2_S2.services;

import fabriziopesaresi.ProgettoBE_U2_S2.entities.Dipendente;
import fabriziopesaresi.ProgettoBE_U2_S2.entities.Prenotazione;
import fabriziopesaresi.ProgettoBE_U2_S2.entities.Viaggio;
import fabriziopesaresi.ProgettoBE_U2_S2.exceptions.BadRequestException;
import fabriziopesaresi.ProgettoBE_U2_S2.payloads.PrenotazioneDTO;
import fabriziopesaresi.ProgettoBE_U2_S2.repositories.PrenotazioneRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PrenotazioniService {

    private final PrenotazioneRepository prenotazioneRepo;
    private final DipendentiService dipendentiService;
    private final ViaggiService viaggiService;

    public PrenotazioniService(PrenotazioneRepository prenotazioneRepo, DipendentiService dipendentiService, ViaggiService viaggiService) {
        this.prenotazioneRepo = prenotazioneRepo;
        this.dipendentiService = dipendentiService;
        this.viaggiService = viaggiService;
    }

    public Prenotazione save(PrenotazioneDTO body) {
        Dipendente dipendente = this.dipendentiService.findById(body.dipendenteId());
        Viaggio viaggio = this.viaggiService.findById(body.viaggioId());

        // Controllo fondamentale: il dipendente ha già un viaggio in quella data specifica?
        if (this.prenotazioneRepo.existsByDipendenteAndViaggioData(dipendente, viaggio.getData())) {
            throw new BadRequestException("Il dipendente ha già una prenotazione per il giorno: " + viaggio.getData());
        }

        Prenotazione nuova = new Prenotazione();
        nuova.setDipendente(dipendente);
        nuova.setViaggio(viaggio);
        nuova.setDataRichiesta(LocalDate.now());
        nuova.setNote(body.note());

        return this.prenotazioneRepo.save(nuova);
    }
}
