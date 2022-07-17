package org.webApp.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    private String username;
    private String password;
    private String description;
    private String role;
}
