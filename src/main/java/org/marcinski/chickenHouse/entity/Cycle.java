package org.marcinski.chickenHouse.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "cycle")
@Data
public class Cycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cycle_id")
    private Long id;

    @NotEmpty(message = "Please provide number of chickens!")
    @Column(name = "number_of_chickens")
    private int numberOfChickens;

    @NotEmpty(message = "Please provide a start day!")
    @Column(name = "start_day")
    private LocalDate startDay;

    @OneToMany
    @JoinColumn(name = "cycle_id")
    private Set<Day> days;
}
