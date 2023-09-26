package com.sarahweisser.winetrackerapi.advice;

import com.sarahweisser.winetrackerapi.dtos.UserDto;
import com.sarahweisser.winetrackerapi.validation.PasswordFailureException;
import com.sarahweisser.winetrackerapi.validation.UserNameExistsException;
import com.sarahweisser.winetrackerapi.validation.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppControllerAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserDto> handleUserNotFoundException(Exception e) {
        UserDto resp = new UserDto(null, e.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNameExistsException.class)
    public ResponseEntity<UserDto> handleUserNameExistsException(Exception e) {
        UserDto resp = new UserDto(null, e.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordFailureException.class)
    public ResponseEntity<UserDto> handleBadPasswordException(Exception e) {
        UserDto resp = new UserDto(null, e.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserDto> handleUserExceptions(Exception e) {
        UserDto resp = new UserDto(null, e.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

}
