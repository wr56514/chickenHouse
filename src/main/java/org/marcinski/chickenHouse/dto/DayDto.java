package org.marcinski.chickenHouse.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class DayDto {

    private Long id;

    @Min(0)
    private int dayNumber;

    @Nullable
    private int naturalDowns;

    @Nullable
    private int selectionDowns;

    @Nullable
    private long waterCounter;

    @Nullable
    private double averageWeight;

    @Nullable
    private String comments;

    private CycleDto cycleDto;
}
