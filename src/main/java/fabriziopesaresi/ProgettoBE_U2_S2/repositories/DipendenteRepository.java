package fabriziopesaresi.ProgettoBE_U2_S2.repositories;

import fabriziopesaresi.ProgettoBE_U2_S2.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DipendenteRepository extends JpaRepository<Dipendente, UUID> {
    boolean existsByEmail(String email);
}