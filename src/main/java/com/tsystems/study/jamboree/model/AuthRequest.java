package com.tsystems.study.jamboree.model;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;
}
