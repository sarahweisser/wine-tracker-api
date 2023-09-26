package com.sarahweisser.winetrackerapi.services;

import com.sarahweisser.winetrackerapi.models.User;
import com.sarahweisser.winetrackerapi.repositories.UserJpaRepository;
import com.sarahweisser.winetrackerapi.validation.PasswordFailureException;
import com.sarahweisser.winetrackerapi.validation.UserNameExistsException;
import com.sarahweisser.winetrackerapi.validation.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findUserById(Long id) {
        Optional<User> existingUser = userJpaRepository.findById(id);
        if (existingUser.isPresent()) {
            return existingUser;
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<User> findAllUsers() {
        return userJpaRepository.findAll();
    }

    @Override
    public User createUser(User userToAdd) {
        Optional<User> existingUser = userJpaRepository.findUsersByUserName(userToAdd.getUserName());
        if (!existingUser.isPresent()) {
            return userJpaRepository.saveAndFlush(userToAdd);
        } else {
            throw new UserNameExistsException();
        }
    }

    @Override
    public User updateUser(User userToUpdate) {
        Optional<User> existingUser = userJpaRepository.findById(userToUpdate.getUserId());

        if (existingUser.isPresent()) {
            return userJpaRepository.saveAndFlush(userToUpdate);
        } else {
            throw new UserNotFoundException();
        }
    }

    public User processUserLogin(User user) {
        Optional<User> existingUser = userJpaRepository.findUsersByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            if (existingUser.get().getUserPassword().equals(user.getUserPassword())) {
                return existingUser.get();
            } else throw new PasswordFailureException();
        } else throw new UserNotFoundException();
    }

    @Override
    public void deleteUserById(Long id) {
        userJpaRepository.deleteById(id);
    }

    public void deleteUsers() {
        userJpaRepository.deleteAll();
    }
}
