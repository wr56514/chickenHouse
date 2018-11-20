package org.marcinski.chickenHouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.marcinski.chickenHouse.dto.UserDto;
import org.marcinski.chickenHouse.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "roleDto", target = "role")
    User mapTo(UserDto userDto);

    @Mapping(source = "role", target = "roleDto")
    UserDto mapTo(User user);
}
