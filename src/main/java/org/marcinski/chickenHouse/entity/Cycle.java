package org.marcinski.chickenHouse.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "cycle")
@Data
@EqualsAndHashCode(exclude = "chickenHouse")
public class Cycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cycle_id")
    private Long id;

    @NotNull(message = "Please provide number of chickens!")
    @Column(name = "number_of_chickens")
    private int numberOfChickens;

    @NotNull(message = "Please provide a start day!")
    @Column(name = "start_day")
    private LocalDate startDay;

    private String hybrid;
    private String hatchery;

    @OneToMany
    @JoinColumn(name = "cycle_id")
    private Set<Day> days;

    @ManyToOne()
    @JoinColumn(name = "house_id")
    @ToString.Exclude
    private ChickenHouse chickenHouse;
}
