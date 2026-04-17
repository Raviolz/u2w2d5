package Raviolz.u2w2d5.services;

import Raviolz.u2w2d5.entities.Employee;
import Raviolz.u2w2d5.exceptions.AlreadyExistEx;
import Raviolz.u2w2d5.payloads.NewEmployeeDTO;
import Raviolz.u2w2d5.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

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

}
