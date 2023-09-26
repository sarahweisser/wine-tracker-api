package com.sarahweisser.winetrackerapi.controllers;

import com.sarahweisser.winetrackerapi.dtos.UserDto;
import com.sarahweisser.winetrackerapi.models.User;
import com.sarahweisser.winetrackerapi.services.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    ResponseEntity<UserDto> findUserById(@PathVariable Long id) {
        UserDto resp = new UserDto(userServiceImpl.findUserById(id).get(), null);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/users")
    ResponseEntity<UserDto> createUser(@RequestBody User user) {
        UserDto resp = new UserDto(userServiceImpl.createUser(user), null);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/users/login")
    ResponseEntity<UserDto> loginUser(@RequestBody User user) {
        UserDto resp = new UserDto(userServiceImpl.processUserLogin(user), null);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    ResponseEntity<UserDto> updateUser(@RequestBody User user) {
        UserDto resp = new UserDto(userServiceImpl.updateUser(user), null);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    void deleteById(@PathVariable Long id) {
        userServiceImpl.deleteUserById(id);
    }
}
