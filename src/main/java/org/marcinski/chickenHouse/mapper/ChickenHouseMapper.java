package org.marcinski.chickenHouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.marcinski.chickenHouse.dto.ChickenHouseDto;
import org.marcinski.chickenHouse.entity.ChickenHouse;

@Mapper(componentModel = "spring")
public interface ChickenHouseMapper {

    ChickenHouseMapper INSTANCE = Mappers.getMapper(ChickenHouseMapper.class);

    @Mapping(source = "userDto", target = "user")
    ChickenHouse mapToChickenHouse(ChickenHouseDto chickenHouseDto);

    @Mapping(source = "user", target = "userDto")
    ChickenHouseDto mapToChickenHouseDto(ChickenHouse chickenHouse);
}
