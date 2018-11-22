package org.marcinski.chickenHouse.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "chicken_house")
@Data
@EqualsAndHashCode(exclude = "cycles")
public class ChickenHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private Long id;

    @NotEmpty(message = "Please provide a name of chicken house")
    private String name;

    @Min(0)
    @Column(name = "area")
    private int areaOfHouse;

    @OneToMany(mappedBy = "chickenHouse", cascade = CascadeType.REMOVE)
    private Set<Cycle> cycles;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
}
