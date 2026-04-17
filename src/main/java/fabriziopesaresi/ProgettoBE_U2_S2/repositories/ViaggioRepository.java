package fabriziopesaresi.ProgettoBE_U2_S2.repositories;

import fabriziopesaresi.ProgettoBE_U2_S2.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ViaggioRepository extends JpaRepository<Viaggio, UUID> {
}