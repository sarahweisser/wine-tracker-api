package com.sarahweisser.winetrackerapi.repositories;

import com.sarahweisser.winetrackerapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findUsersByUserName(String userName);
}

