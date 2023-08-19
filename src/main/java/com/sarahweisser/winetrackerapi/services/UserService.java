package com.sarahweisser.winetrackerapi.services;

import com.sarahweisser.winetrackerapi.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Long id);
    List<User> findAllUsers();
    User createUser(User userToAdd);
    User updateUser(User userToUpdate) throws Exception;
}
