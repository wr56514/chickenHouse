package org.marcinski.chickenHouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.marcinski.chickenHouse.dto.UserDto;
import org.marcinski.chickenHouse.entity.User;

@Mapper(componentModel = "spring", uses = {ChickenHouseMapper.class})
public interface UserMapper {

    @Mapping(source = "roleDto", target = "role")
    @Mapping(source = "chickenHouseDtos", target = "chickenHouses")
    User mapTo(UserDto userDto);

    @Mapping(source = "role", target = "roleDto")
    @Mapping(source = "chickenHouses", target = "chickenHouseDtos")
    UserDto mapTo(User user);
}
