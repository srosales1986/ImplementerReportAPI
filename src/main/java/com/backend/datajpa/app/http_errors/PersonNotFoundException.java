package com.backend.datajpa.app.http_errors;

public class PersonNotFoundException extends RuntimeException {

    private static final String DESCRIPTION = "Person not found";

    public PersonNotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
