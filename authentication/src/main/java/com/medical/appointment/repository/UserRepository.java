package com.medical.appointment.repository;

import com.medical.appointment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findUserByUserNameOrUserEmail(String userName , String userEmail);
    User findUserByUserName(String userName);
    User findUserByUserEmail(String userEmail);
    List<User> findUsersByUserNameContainingIgnoreCase(String userName);
}
