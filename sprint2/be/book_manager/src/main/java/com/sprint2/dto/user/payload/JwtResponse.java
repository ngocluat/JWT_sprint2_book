package com.sprint2.dto.user.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 #* Function: this class use for send all of information to client with JWT token
 #*/
@JsonIgnoreProperties({"password"})
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private String password;
    private List<String> roles;

    public JwtResponse(String token, String username, String password, List<String> roles) {
        this.token = token;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
