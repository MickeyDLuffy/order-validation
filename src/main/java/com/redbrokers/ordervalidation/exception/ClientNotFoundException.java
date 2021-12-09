package com.redbrokers.ordervalidation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

public class ClientNotFoundException extends ResponseStatusException {
    public ClientNotFoundException() {
        super(HttpStatus.NOT_FOUND, "No client exists with that client id");
    }
}
