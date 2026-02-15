package com.medical.appointment.dto;

import com.medical.appointment.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    private boolean enabled;
    private Role role;
}
