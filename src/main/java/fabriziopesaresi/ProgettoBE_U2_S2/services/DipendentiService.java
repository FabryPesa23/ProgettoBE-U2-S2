package fabriziopesaresi.ProgettoBE_U2_S2.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class DipendentiService {

    private final DipendenteRepository dipendenteRepository;
    private final Cloudinary cloudinaryUploader;

    public DipendentiService(DipendenteRepository dipendenteRepository, Cloudinary cloudinaryUploader) {
        this.dipendenteRepository = dipendenteRepository;
        this.cloudinaryUploader = cloudinaryUploader;
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

    public Dipendente findByIdAndUpdate(UUID id, DipendenteDTO body) {
        Dipendente found = this.findById(id);

        // Se l'email viene cambiata, controlliamo che la nuova non sia già di qualcun altro
        if (!found.getEmail().equals(body.email()) && this.dipendenteRepository.existsByEmail(body.email())) {
            throw new BadRequestException("L'email " + body.email() + " è già in uso!");
        }

        found.setNome(body.nome());
        found.setCognome(body.cognome());
        found.setUsername(body.username());
        found.setEmail(body.email());

        return this.dipendenteRepository.save(found);
    }

    public void findByIdAndDelete(UUID id) {
        Dipendente found = this.findById(id);
        this.dipendenteRepository.delete(found);
    }

    public Dipendente uploadAvatar(UUID id, MultipartFile file) throws IOException {
        Dipendente found = this.findById(id);
        String avatarUrl = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatar(avatarUrl);
        return this.dipendenteRepository.save(found);
    }
}