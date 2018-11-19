package org.marcinski.chickenHouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.marcinski.chickenHouse.dto.UserDto;
import org.marcinski.chickenHouse.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapTo(User user);
    User mapTo(UserDto userDto);
}
