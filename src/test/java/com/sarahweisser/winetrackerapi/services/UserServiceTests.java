package com.sarahweisser.winetrackerapi.services;

import com.sarahweisser.winetrackerapi.models.User;
import com.sarahweisser.winetrackerapi.repositories.UserJpaRepository;
import com.sarahweisser.winetrackerapi.validation.PasswordFailureException;
import com.sarahweisser.winetrackerapi.validation.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserJpaRepository userJpaRepository;

    private List<User> userList = new ArrayList();
    private User user1 = new User(1L, "aa", "aa", true);
    private User user2 = new User(2L, "as", "as", false);

    private User nonUser = new User(0L, "non_user", "pass", false);
    private User badPass = new User(1L, "aa", "bad_pass", false);

    @BeforeEach
    public void setUpTestData() {
        this.userList.add(user1);
        this.userList.add(user2);
        when(userJpaRepository.findUsersByUserName("aa")).thenReturn(Optional.of(user1));
    }

    @Test
    @DisplayName("Test processUserLogin")
    void testProcessUserLogin() {
        User existingUser = userService.processUserLogin(user1);
        Assertions.assertEquals(existingUser.getUserId(), user1.getUserId());
    }

    @Test
    @DisplayName("Test processUserLogin user not found")
    void testProcessUserLoginNotFound() {
        Assertions.assertThrows(UserNotFoundException.class,
                () -> {
                    userService.processUserLogin(this.nonUser);
        });
    }

    @Test
    @DisplayName("Test processUserLogin bad password")
    void testProcessUserLoginBadPass() {
        Assertions.assertThrows(PasswordFailureException.class,
                () -> {
                    userService.processUserLogin(this.badPass);
                });
    }
}
