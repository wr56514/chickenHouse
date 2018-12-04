package org.marcinski.chickenHouse.mapper;

import org.mapstruct.Mapper;
import org.marcinski.chickenHouse.dto.ForageDto;
import org.marcinski.chickenHouse.entity.Forage;

@Mapper(componentModel = "spring")
public interface ForageMapper {

    Forage mapTo(ForageDto dayDto);

    ForageDto mapTo(Forage day);
}
