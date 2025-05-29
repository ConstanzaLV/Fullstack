package com.edutech.userservice.service.domain;

import lombok.Setter;

public class User {
    @Setter
    private Long id;
    private String username;
    private String email;
    private String role;
    private String status;

    public User(Long id, String username, String email, String role, String status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public Long getId()        { return id; }
    public String getUsername(){ return username; }
    public String getEmail()   { return email; }
    public String getRole()    { return role; }
    public String getStatus()  { return status; }
}
