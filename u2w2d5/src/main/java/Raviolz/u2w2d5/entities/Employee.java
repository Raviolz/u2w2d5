package Raviolz.u2w2d5.entities;

import Raviolz.u2w2d5.exceptions.ValidationEx;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Entity
@NoArgsConstructor
@Setter
@Getter


@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String avatar;
    @Column(nullable = false)
    private String password;


    public Employee(String username, String name, String surname, String email, String password) {
        if (username == null || username.isBlank()) {
            throw new ValidationEx("Username obbligatorio");
        }
        if (name == null || name.isBlank()) {
            throw new ValidationEx("Nome obbligatorio");
        }
        if (surname == null || surname.isBlank()) {
            throw new ValidationEx("Cognome obbligatorio");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new ValidationEx("Email non valida");
        }
        if (email == null || email.isBlank()) {
            throw new ValidationEx("Email obbligatoria");
        }
// TODO fare controlli nei setter se ho tempo
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.avatar = "https://ui-avatars.com/api/?name=" + name + "+" + surname;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", id=" + id +
                '}';
    }
}
