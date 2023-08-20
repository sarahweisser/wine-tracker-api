package com.sarahweisser.winetrackerapi.services;

import com.sarahweisser.winetrackerapi.models.User;
import com.sarahweisser.winetrackerapi.repositories.UserJpaRepository;
import com.sarahweisser.winetrackerapi.validation.PasswordFailureException;
import com.sarahweisser.winetrackerapi.validation.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserJpaRepository userJpaRepository;

    public UserServiceImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userJpaRepository.findById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userJpaRepository.findAll();
    }

    @Override
    public User createUser(User userToAdd) {
        return userJpaRepository.saveAndFlush(userToAdd);
    }

    @Override
    public User updateUser(User userToUpdate) throws Exception {
        try {
            Optional<User> existingUser = userJpaRepository.findById(userToUpdate.getUserId());
            if (existingUser.isPresent()) {
                return userJpaRepository.saveAndFlush(userToUpdate);
            } else {
                throw new UserNotFoundException();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public User processUserLogin(User user) {
        System.out.println("In processUserLogin");

        // TODO validate input
        Optional<User> existingUser = userJpaRepository.findUsersByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            System.out.println(existingUser.get().getUserPassword());
            if (existingUser.get().getUserPassword().equals(user.getUserPassword())) {
                return user;
            } else throw new PasswordFailureException();
        } else throw new UserNotFoundException();
    }

    @Override
    public void deleteUserById(Long id) {
        userJpaRepository.deleteById(id);
    }
}
