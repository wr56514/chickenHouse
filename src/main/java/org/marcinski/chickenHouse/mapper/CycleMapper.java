package org.marcinski.chickenHouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.marcinski.chickenHouse.dto.CycleDto;
import org.marcinski.chickenHouse.entity.Cycle;

@Mapper(componentModel = "spring")
public interface CycleMapper {

    CycleMapper INSTANCE = Mappers.getMapper(CycleMapper.class);

    @Mapping(source = "chickenHouseDto", target = "chickenHouse")
    Cycle mapTo(CycleDto cycleDto);

    @Mapping(source = "chickenHouse", target = "chickenHouseDto")
    CycleDto mapTo(Cycle cycle);
}
