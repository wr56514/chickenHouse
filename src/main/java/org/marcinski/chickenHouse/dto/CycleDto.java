package org.marcinski.chickenHouse.dto;

import lombok.Data;
import org.marcinski.chickenHouse.entity.Day;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Data
public class CycleDto {

    private Long id;

    @NotEmpty(message = "Please provide number of chickens!")
    private int numberOfChickens;

    @NotEmpty(message = "Please provide a start day!")
    private LocalDate startDay;
    private Set<DayDto> days;
}
