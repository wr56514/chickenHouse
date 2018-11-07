package org.marcinski.chickenHouse.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class ChickenHouseDto {

    private Long id;

    @NotEmpty(message = "Please provide a name of chicken house")
    private String name;
    @NotEmpty(message = "Please provide area!")
    private int areaOfHouse;

    private Set<CycleDto> cyclesDto;

    private UserDto userDto;
}
