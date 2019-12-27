package com.tsystems.study.jamboree.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String login;
    private String fullName;
    private String password;
    private boolean admin;
}
