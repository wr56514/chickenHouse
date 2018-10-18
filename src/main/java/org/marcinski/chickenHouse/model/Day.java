package org.marcinski.chickenHouse.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "day_of_cycle")
@Data
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "day_number")
    private int dayNumber;

    @Nullable
    @Column(name = "natural_downs")
    private int naturalDowns;

    @Nullable
    @Column(name = "selection_downs")
    private int selectionDowns;

    @Nullable
    @Column(name = "water_counter")
    private long waterCounter;
}
