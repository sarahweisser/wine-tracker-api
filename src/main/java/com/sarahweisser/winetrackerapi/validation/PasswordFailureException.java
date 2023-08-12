package com.sarahweisser.winetrackerapi.validation;

public class PasswordFailureException extends RuntimeException {
    public PasswordFailureException () {
        super(ExceptionMessages.INCORRECT_PASS);
    }
}
