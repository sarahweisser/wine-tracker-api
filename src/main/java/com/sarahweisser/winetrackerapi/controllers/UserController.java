package com.sarahweisser.winetrackerapi.controllers;

import com.sarahweisser.winetrackerapi.models.User;
import com.sarahweisser.winetrackerapi.services.UserServiceImpl;
import com.sarahweisser.winetrackerapi.validation.ExceptionMessages;
import com.sarahweisser.winetrackerapi.validation.PasswordFailureException;
import com.sarahweisser.winetrackerapi.validation.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/users")
    Iterable<User> findAllUsers() {
        return userServiceImpl.findAllUsers();
    }

    @GetMapping("/users/{id}")
    ResponseEntity<Optional<User>> findUserById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(userServiceImpl.findUserById(id), HttpStatus.OK);
        } catch (UserNotFoundException unfe) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionMessages.USER_NOT_FOUND);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.SEE_OTHER, ExceptionMessages.MISC_ERROR);
        }
    }

    @PostMapping("/users")
    ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userServiceImpl.createUser(user), HttpStatus.OK);
    }

    @PostMapping("/users/login")
    ResponseEntity<User> loginUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userServiceImpl.processUserLogin(user), HttpStatus.OK);
        } catch (UserNotFoundException unfe) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionMessages.USER_NOT_FOUND);
        } catch (PasswordFailureException pfe) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, ExceptionMessages.INCORRECT_PASS);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.SEE_OTHER, ExceptionMessages.MISC_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    ResponseEntity<User> updateUser(@RequestBody User user) {
        Optional<User> existingUser = userServiceImpl.findUserById(user.getUserId());
        if (existingUser.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    void deleteById(@PathVariable Long id) {
        userServiceImpl.deleteById(id);
    }
}
