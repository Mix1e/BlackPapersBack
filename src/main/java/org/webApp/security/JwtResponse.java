package org.webApp.security;

import lombok.Data;

@Data
public class JwtResponse {

    private String type = "Bearer ";
    private String token;
    private String nickname;
    private String description;
    private String role;


    public JwtResponse(String token, String nickname, String description, String role) {
        this.token = type + token;
        this.nickname = nickname;
        this.description = description;
        this.role = role;
    }

}
