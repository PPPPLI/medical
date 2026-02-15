package com.medical.appointment.service.impl;

import com.medical.appointment.dto.ResponseDto;
import com.medical.appointment.dto.UserDto;
import com.medical.appointment.entity.User;
import com.medical.appointment.enums.Role;
import com.medical.appointment.exception.AuthenticationException;
import com.medical.appointment.mapper.UserMapper;
import com.medical.appointment.repository.UserRepository;
import com.medical.appointment.service.UserService;
import com.medical.appointment.utils.JWTUtils;
import com.medical.appointment.utils.PasswdVerifyUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

     private final UserRepository userRepository;
     private final UserMapper userMapper;
     private final JWTUtils jwtUtils;
     private final PasswdVerifyUtils passwdVerifyUtils;

     public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, JWTUtils jwtUtils, PasswdVerifyUtils passwdVerifyUtils) {
         this.userRepository = userRepository;
         this.userMapper = userMapper;
         this.jwtUtils = jwtUtils;
         this.passwdVerifyUtils = passwdVerifyUtils;
     }

    @Override
    public UserDto getUsersByUsername(String username) {

         User user = userRepository.findUserByUserName(username);

         if(user != null){

             return removePasswordAndToDto(user);
         }

         return null;
    }

    @Override
    public List<UserDto> getUsersFilteringByUsername(String username) {
        return userRepository.findUsersByUserNameContainingIgnoreCase(username)
                .stream()
                .map(this::removePasswordAndToDto)
                .toList();
    }

    @Override
    public UserDto getUserById(UUID id) {

        User user = userRepository.findById(id).orElse(null);
        if(user != null) {

            return removePasswordAndToDto(user);
        }

        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::removePasswordAndToDto)
                .toList();
    }

    @Override
    public void saveUser(UserDto userDto) {

         User user = userMapper.toUser(userDto);

         if(userRepository.findUserByUserName(userDto.getUserName()) != null ||
            userRepository.findUserByUserEmail(userDto.getUserEmail()) != null
         ){

             throw new AuthenticationException("Username already exists");
         }

         user.setEnabled(true);
         user.setUserPassword(jwtUtils.passwordEncoder().encode(userDto.getUserPassword()));
         user.setCreateAt(LocalDateTime.now());
         user.setUpdateAt(LocalDateTime.now());
         user.setRole(Role.USER);
         userRepository.save(user);
    }

    @Override
    public void updateUser(UserDto userDto) {

         User user = userRepository.findById(userDto.getUserId()).orElse(null);

         if(user == null){
             throw new AuthenticationException("User does not exist");
         }

        User userToUpdate = userMapper.toUser(userDto);

        if(userDto.getUserName() != null &&!user.getUserName().equals(userDto.getUserName())){

             User userWithName = userRepository.findUserByUserName(userDto.getUserName());
             if(userWithName != null){

                 throw new AuthenticationException("Username already exists");
             }
         }

        if(userDto.getUserName() == null){

            userToUpdate.setUserName(user.getUserName());
        }

        if(userDto.getUserEmail() == null){

            userToUpdate.setUserEmail(user.getUserEmail());
        }


        if(userDto.getUserPassword() != null && !passwdVerifyUtils.verifyPassword(userDto.getUserPassword(), user.getUserPassword())){

            userToUpdate.setUserPassword(jwtUtils.passwordEncoder().encode(userDto.getUserPassword()));
         }else{

            userToUpdate.setUserPassword(user.getUserPassword());
        }

        userToUpdate.setCreateAt(user.getCreateAt());
        userToUpdate.setUpdateAt(LocalDateTime.now());
        userToUpdate.setUserId(user.getUserId());
        userToUpdate.setRole(Role.USER);
        userToUpdate.setEnabled(true);

         userRepository.save(userToUpdate);
    }

    @Override
    public void deleteUser(UUID id) {

         User user = userRepository.findById(id).orElse(null);
         if(user == null){
             throw new AuthenticationException("User does not exist");
         }

         user.setEnabled(false);
         userRepository.save(user);
    }

    @Override
    public ResponseDto login(UserDto userDto) {

         User user = userRepository.findUserByUserNameOrUserEmail(userDto.getUserEmail(),userDto.getUserEmail());

         if(user == null){
             throw new AuthenticationException("Username not found");
         }


         if(!passwdVerifyUtils.verifyPassword(userDto.getUserPassword(),user.getUserPassword())){

             throw new AuthenticationException("Wrong password");
         }

         if(!user.isEnabled()){

             throw new AuthenticationException("User is disabled");
         }

         return ResponseDto.builder()
                 .token(jwtUtils.createJwt(user.getUserName(),user.getRole().toString()))
                 .userDto(removePasswordAndToDto(user))
                 .build();
    }

    private UserDto removePasswordAndToDto(User user) {

        UserDto userDto = userMapper.toUserDto(user);
        userDto.setUserPassword(null);
        return userDto;
    }
}
