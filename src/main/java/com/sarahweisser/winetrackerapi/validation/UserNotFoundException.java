package com.sarahweisser.winetrackerapi.validation;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(ExceptionMessages.USER_NOT_FOUND);
    }
}
