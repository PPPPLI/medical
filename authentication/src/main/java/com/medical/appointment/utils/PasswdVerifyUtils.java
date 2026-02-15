package com.medical.appointment.utils;

import org.springframework.stereotype.Component;

@Component
public class PasswdVerifyUtils {

    private final JWTUtils jwtUtils;

    public PasswdVerifyUtils(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    public boolean verifyPassword(String password,String encodedPassword){

        return jwtUtils.passwordEncoder().matches(password,encodedPassword);
    }
}
