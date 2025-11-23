package com.example.biblio_security.dto.security;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
