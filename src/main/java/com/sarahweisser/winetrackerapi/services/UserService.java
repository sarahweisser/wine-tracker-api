package com.sarahweisser.winetrackerapi.services;

import com.sarahweisser.winetrackerapi.models.User;
import com.sarahweisser.winetrackerapi.repositories.UserJpaRepository;
import com.sarahweisser.winetrackerapi.validation.PasswordFailureException;
import com.sarahweisser.winetrackerapi.validation.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserJpaRepository userJpaRepository;

//    public UserService() {}

//    public UserService(UserJpaRepository userJpaRepository) {
//        this.userJpaRepository = userJpaRepository;
//    }

    public User processUserLogin(User user) {
        System.out.println("In processUserLogin");

        // TODO validate input
        //System.out.println(user.getUserName());
        Optional<User> existingUser = userJpaRepository.findUsersByUserName(user.getUserName());

        if (existingUser.isPresent()) {
            System.out.println(existingUser.get().getUserPassword());
            if (existingUser.get().getUserPassword().equals(user.getUserPassword())) {

                return user;
            } else throw new PasswordFailureException();
        } else throw new UserNotFoundException();
    }
}
