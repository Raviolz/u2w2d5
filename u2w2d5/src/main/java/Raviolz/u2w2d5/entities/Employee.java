package Raviolz.u2w2d5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString

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


    public Employee(String username, String name, String surname, String email) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.avatar = "https://ui-avatars.com/api/?name=" + name + "+" + surname;
    }
}
