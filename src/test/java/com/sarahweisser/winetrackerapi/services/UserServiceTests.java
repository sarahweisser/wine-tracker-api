package com.sarahweisser.winetrackerapi.services;

import com.sarahweisser.winetrackerapi.models.User;
import com.sarahweisser.winetrackerapi.validation.PasswordFailureException;
import com.sarahweisser.winetrackerapi.validation.UserNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserServiceImpl userService;
    private User user1 = new User(1L, "aa", "aa", true);
    private User user2 = new User(2L, "as", "as", false);
    private User nonUser = new User(0L, "non_user", "pass", false);
    private User badPass = new User(1L, "aa", "bad_pass", false);
    private User updatedUser = new User(1L, "aa", "new_pass", false);
    @BeforeEach
    public void setUpUserTestData() {
        userService.createUser(user1);
        userService.createUser(user2);
    }

    @Test
    @DisplayName("testFindAllUsers")
     void testFindAllUsers() {
        Assertions.assertEquals(2, userService.findAllUsers().size());
    }

    @Test
    @DisplayName("testProcessUserLogin")
    void testProcessUserLogin() {
        User existingUser = userService.processUserLogin(user1);
        Assertions.assertEquals(existingUser.getUserId(), user1.getUserId());
    }

    @Test
    @DisplayName("testProcessUserLoginNotFound")
    void testProcessUserLoginNotFound() {
        Assertions.assertThrows(UserNotFoundException.class,
                () -> {
                    userService.processUserLogin(this.nonUser);
        });
    }

    @Test
    @DisplayName("testProcessUserLoginBadPass")
    void testProcessUserLoginBadPass() {
        Assertions.assertThrows(PasswordFailureException.class,
                () -> {
                    userService.processUserLogin(this.badPass);
                });
    }

    @Test
    @DisplayName("testUpdateUser")
    void testUpdateUser() throws Exception {
        User updatedUser = userService.updateUser(this.updatedUser);

        Assertions.assertEquals(updatedUser.getUserId(),
                userService.findUserById(updatedUser.getUserId()).get().getUserId());
        Assertions.assertEquals(updatedUser.getUserPassword(),
                userService.findUserById(updatedUser.getUserId()).get().getUserPassword());
    }

    @Test
    @DisplayName("testUpdateUserNotFound")
    void testUpdateUserNotFound() {
        Assertions.assertThrows(UserNotFoundException.class,
                () -> {
                    userService.updateUser(this.nonUser);
                });
    }

    @Test
    @DisplayName("testDeleteById")
    void testDeleteById() {
        Assertions.assertEquals(user2.getUserId(),
                userService.findUserById(user2.getUserId()).get().getUserId());
        userService.deleteUserById(user2.getUserId());
        Optional<User> deletedUser = userService.findUserById(user2.getUserId());
        Assertions.assertFalse(deletedUser.isPresent());
    }
}
