package org.marcinski.chickenHouse.dto;

import lombok.Data;
import org.marcinski.chickenHouse.entity.Day;

@Data
public class MedicineDto {

    private Long id;
    private String name;
    private String price;
    private Day day;
}
