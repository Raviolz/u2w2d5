package Raviolz.u2w2d5.repositories;

import Raviolz.u2w2d5.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
