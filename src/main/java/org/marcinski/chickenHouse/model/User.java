package org.marcinski.chickenHouse.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Email(message = "Wpisz email w poprawnej formie!")
    @NotEmpty(message = "Wpisz email!")
    private String email;

    @NotEmpty(message = "Wpisz hasło!")
    @Length(min = 5, message = "Hasło musi mieć przynajmniej 5 znaków!")
    private String password;

    @NotEmpty(message ="Wpisz swoje imie!")
    private String name;

    private boolean active;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<ChickenHouse> chickenHouses;
}
