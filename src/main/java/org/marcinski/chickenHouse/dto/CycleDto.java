package org.marcinski.chickenHouse.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Data
public class CycleDto {

    private Long id;

    @NotNull(message = "Please provide number of chickens!")
    private int numberOfChickens;

    @NotNull(message = "Please provide a start day!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDay;

    private String hybrid;
    private String hatchery;
    private boolean completed;

    private Set<DayDto> daysDto;

    private ChickenHouseDto chickenHouseDto;
}
