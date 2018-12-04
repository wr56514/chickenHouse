package org.marcinski.chickenHouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.marcinski.chickenHouse.dto.CycleDto;
import org.marcinski.chickenHouse.entity.Cycle;

@Mapper(componentModel = "spring", uses = {DayMapper.class})
public interface CycleMapper {

    @Mapping(source = "chickenHouseDto", target = "chickenHouse")
    @Mapping(source = "daysDto", target = "days")
    Cycle mapTo(CycleDto cycleDto);

    @Mapping(source = "chickenHouse", target = "chickenHouseDto")
    @Mapping(source = "days", target = "daysDto")
    CycleDto mapTo(Cycle cycle);
}
