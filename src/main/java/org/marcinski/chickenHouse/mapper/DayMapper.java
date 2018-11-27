package org.marcinski.chickenHouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.marcinski.chickenHouse.dto.DayDto;
import org.marcinski.chickenHouse.entity.Day;

@Mapper(componentModel = "spring")
public interface DayMapper {

    DayMapper INSTANCE = Mappers.getMapper(DayMapper.class);

    @Mapping(source = "cycleDto", target = "cycle")
    Day mapTo(DayDto dayDto);
}
