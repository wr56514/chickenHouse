package org.marcinski.chickenHouse.dto;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;

public class DayDto {

    private Long id;

    @NotEmpty
    private int dayNumber;

    @Nullable
    private int naturalDowns;

    @Nullable
    private int selectionDowns;

    @Nullable
    private long waterCounter;
}
