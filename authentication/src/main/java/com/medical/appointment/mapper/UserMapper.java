package com.medical.appointment.mapper;

import com.medical.appointment.dto.UserDto;
import com.medical.appointment.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);
    User toUser(UserDto userDto);
}
