package com.medical.appointment.controller;

import com.medical.appointment.dto.ResponseDto;
import com.medical.appointment.dto.UserDto;
import com.medical.appointment.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto user) {

        userService.saveUser(user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody UserDto user) {

        return ResponseEntity.ok(userService.login(user));
    }

    @GetMapping("/get/{id}")
    public  ResponseEntity<UserDto> getUser(@PathVariable("id") UUID id) {

        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/get/all")
    @PostAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get/{filter}")
    public  ResponseEntity<List<UserDto>> getUserByFilter(@PathVariable("filter") String filter) {

        return ResponseEntity.ok(userService.getUsersFilteringByUsername(filter));
    }

    @GetMapping("/get/{username}")
    public  ResponseEntity<UserDto> getUserByUsername(@PathVariable("username") String username) {

        return ResponseEntity.ok(userService.getUsersByUsername(username));
    }

    @PatchMapping("/update")
    @PostAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> update(@RequestBody UserDto user) {
        userService.updateUser(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    @PostAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
