package org.marcinski.chickenHouse.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "chicken_house")
@Data
public class ChickenHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private Long id;

    @NotEmpty(message = "Please provide a name of chicken house")
    private String name;

    @NotEmpty(message = "Please provide area!")
    @Column(name = "area")
    private int areaOfHouse;

    @OneToMany
    @JoinColumn(name = "house_id")
    private Set<Cycle> cycles;
}
