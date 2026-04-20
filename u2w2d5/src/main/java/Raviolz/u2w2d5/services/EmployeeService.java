package Raviolz.u2w2d5.services;

import Raviolz.u2w2d5.entities.Employee;
import Raviolz.u2w2d5.exceptions.AlreadyExistEx;
import Raviolz.u2w2d5.exceptions.BadRequestEx;
import Raviolz.u2w2d5.exceptions.NotFoundEx;
import Raviolz.u2w2d5.payloads.NewEmployeeDTO;
import Raviolz.u2w2d5.payloads.UpdatedEmployeeDTO;
import Raviolz.u2w2d5.repositories.EmployeeRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository eRep;
    private final Cloudinary cloudinaryUploader;

    public EmployeeService(EmployeeRepository eRep, Cloudinary cloudinaryUploader) {
        this.eRep = eRep;
        this.cloudinaryUploader = cloudinaryUploader;
    }

    public Employee save(NewEmployeeDTO body) {

        if (eRep.existsByEmail(body.email())) {
            throw new AlreadyExistEx("L'email " + body.email() + " e' gia' in uso");
        }

        if (eRep.existsByUsername(body.username())) {
            throw new AlreadyExistEx("Username " + body.username() + " gia' utlizzato");
        }

        Employee newEmployee = new Employee( //dto no get
                body.username(),
                body.name(),
                body.surname(),
                body.email(),
                body.password()
        );

        return eRep.save(newEmployee);
    }

    public Page<Employee> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return eRep.findAll(pageable);
    }

    public Employee findById(UUID id) {
        return eRep.findById(id)
                .orElseThrow(() -> new NotFoundEx("Dipendente con id " + id + " non trovato"));
    }

    public Employee findByEmail(String email) {
        return eRep.findByEmail(email)
                .orElseThrow(() -> new NotFoundEx("Dipendente con email " + email + " non trovato"));
    }

    public Employee findByIdAndUpdate(UUID id, UpdatedEmployeeDTO body) { // uso lo stesso anche se il nome non torna molto
        Employee found = this.findById(id);

        if (!found.getEmail().equals(body.email())) {
            if (eRep.existsByEmail(body.email())) {
                throw new AlreadyExistEx("L'email " + body.email() + " è già in uso");
            }
        }

        if (!found.getUsername().equals(body.username())) {
            if (eRep.existsByUsername(body.username())) {
                throw new AlreadyExistEx("Username " + body.username() + " già in uso");
            }
        }

        found.setUsername(body.username());
        found.setName(body.name());
        found.setSurname(body.surname());
        found.setEmail(body.email());
        found.setAvatar("https://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname());

        return eRep.save(found);
    }

    public void findByIdAndDelete(UUID id) {
        Employee found = this.findById(id);
        eRep.delete(found);
    }

    public void avatarUpload(MultipartFile file, UUID employeeId) {
        // posso fare controlli tipo di file grandezza ecc
        // find .. upload .. cloudinary torna url, metto in db con setavatarurl, save
        if (file.isEmpty()) {
            throw new BadRequestEx("Il file è vuoto");
        }
        Employee found = this.findById(employeeId);
        try { // e' un metodo che lancia un eccezione non runtime obbligatoriamente bisogna gestirla o con throw o con try catch
            // vuole un cast map perche non sa cosi gli torna quindi faccio cosi
            Map result = cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()); // ha opzioni aggiuntive se voglio. Primo attributo il file cosi con getbytes come vogliono loroe secondo map vuota
            String avatarUrl = (String) result.get("secure_url"); // effettivo url da salvare nel DB volendo si puo tornare come risposta invece che mettere il metodo void
            // valore di ritorno e return
            found.setAvatar(avatarUrl);
            eRep.save(found);
            System.out.println(avatarUrl);

        } catch (IOException e) {  // E' OBBLIGATORIO PASSARGLI UNA MAP ANCHE SE VUOTA PERCHE VOGLIONO COSI IN DOC
            throw new RuntimeException(e);
        }
// volendo si puo' tornar user con nuovo avatar
    }
}
