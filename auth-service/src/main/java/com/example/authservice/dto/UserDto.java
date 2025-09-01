package com.example.authservice.dto;

import lombok.Data;
import java.util.Set;

@Data
public class UserDto {
    private String userId;
    private String email;
    private String name;
    private String password; // Only used for creation
    private String encryptedPwd;
    private Set<String> roles;
}
