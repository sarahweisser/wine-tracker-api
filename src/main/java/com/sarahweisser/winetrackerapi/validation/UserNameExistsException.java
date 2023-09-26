package com.sarahweisser.winetrackerapi.validation;

public class UserNameExistsException extends RuntimeException {
    public UserNameExistsException() {
        super(ExceptionMessages.USER_NAME_NOT_UNIQUE);
    }
}
