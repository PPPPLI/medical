package com.medical.appointment.entity;

import com.medical.appointment.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(indexes = {
        @Index(name = "name_index", columnList = "userName", unique = true),
        @Index(name = "email_index", columnList = "userEmail", unique = true)})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean enabled;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
