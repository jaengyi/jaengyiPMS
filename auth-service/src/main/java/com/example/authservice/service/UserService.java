package com.example.authservice.service;

import com.example.authservice.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDetails);
    UserDto getUserByUserId(String userId);
    UserDto getUserDetailsByEmail(String email);
}
