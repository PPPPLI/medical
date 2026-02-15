package com.medical.appointment.service;

import com.medical.appointment.dto.ResponseDto;
import com.medical.appointment.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDto getUsersByUsername(String username);
    ResponseDto login(UserDto userDto);
    List<UserDto> getUsersFilteringByUsername(String username);
    UserDto getUserById(UUID id);
    List<UserDto> getAllUsers();
    void saveUser(UserDto userDto);
    void updateUser(UserDto userDto);
    void deleteUser(UUID id);
}
