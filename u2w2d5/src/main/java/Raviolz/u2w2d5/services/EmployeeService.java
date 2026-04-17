package Raviolz.u2w2d5.services;

import Raviolz.u2w2d5.entities.Employee;
import Raviolz.u2w2d5.exceptions.AlreadyExistEx;
import Raviolz.u2w2d5.exceptions.NotFoundEx;
import Raviolz.u2w2d5.payloads.NewEmployeeDTO;
import Raviolz.u2w2d5.repositories.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository eRep;

    public EmployeeService(EmployeeRepository eRep) {
        this.eRep = eRep;
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
                body.email()
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

    public Employee findByIdAndUpdate(UUID id, NewEmployeeDTO body) { // uso lo stesso anche se il nome non torna molto
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
}
