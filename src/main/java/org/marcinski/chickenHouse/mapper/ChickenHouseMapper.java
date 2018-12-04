package org.marcinski.chickenHouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.marcinski.chickenHouse.dto.ChickenHouseDto;
import org.marcinski.chickenHouse.entity.ChickenHouse;

@Mapper(componentModel = "spring", uses = {CycleMapper.class})
public interface ChickenHouseMapper {

    @Mapping(source = "userDto", target = "user")
    ChickenHouse mapTo(ChickenHouseDto chickenHouseDto);

    @Mapping(source = "user", target = "userDto")
    ChickenHouseDto mapTo(ChickenHouse chickenHouse);
}
