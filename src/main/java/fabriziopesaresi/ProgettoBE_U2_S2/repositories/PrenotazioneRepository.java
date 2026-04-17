package fabriziopesaresi.ProgettoBE_U2_S2.repositories;

import fabriziopesaresi.ProgettoBE_U2_S2.entities.Dipendente;
import fabriziopesaresi.ProgettoBE_U2_S2.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.UUID;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
    boolean existsByDipendenteAndViaggioData(Dipendente dipendente, LocalDate data);
}