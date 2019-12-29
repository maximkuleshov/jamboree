package com.tsystems.study.jamboree.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class JamboreeException extends Exception {

    public JamboreeException(String message) {
        super(message);
    }

    public JamboreeException(String message, Throwable cause) {
        super(message, cause);
    }
}
