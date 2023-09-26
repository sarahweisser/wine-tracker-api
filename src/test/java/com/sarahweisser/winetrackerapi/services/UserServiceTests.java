package com.sarahweisser.winetrackerapi.services;

import com.sarahweisser.winetrackerapi.models.User;
import com.sarahweisser.winetrackerapi.validation.PasswordFailureException;
import com.sarahweisser.winetrackerapi.validation.UserNameExistsException;
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

    @BeforeEach
    public void setUpUserTestData() {
        userService.deleteUsers();
        userService.createUser(user1);
        userService.createUser(user2);
    }

    @Test
    @DisplayName("testCreateUser")
    void testCreateUser() {
        User newUser = new User(0L, "NEW_USER", "pass", false);
        User createdUser = userService.createUser(newUser);
        Assertions.assertEquals(newUser.getUserName(), createdUser.getUserName());
    }

    @Test
    @DisplayName("testCreateUserNameExists")
    void testCreateUserNameExists() {
        User newUser = new User(0L, "aa", "pass", false);
        Assertions.assertThrows(UserNameExistsException.class,
                () -> {
                    userService.createUser(newUser);
                });
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
        Assertions.assertEquals(existingUser.getUserPassword(), user1.getUserPassword());
    }

    @Test
    @DisplayName("testProcessUserLoginNotFound")
    void testProcessUserLoginNotFound() {
        User nonUser = new User(0L, "non_user", "pass", false);
        Assertions.assertThrows(UserNotFoundException.class,
                () -> {
                    userService.processUserLogin(nonUser);
        });
    }

    @Test
    @DisplayName("testProcessUserLoginBadPass")
    void testProcessUserLoginBadPass() {
        User badPass = new User(1L, "aa", "bad_pass", false);
        Assertions.assertThrows(PasswordFailureException.class,
                () -> {
                    userService.processUserLogin(badPass);
                });
    }

    @Test
    @DisplayName("testUpdateUser")
    void testUpdateUser() throws Exception {
        User existingUser = userService.findAllUsers().get(0);
        existingUser.setUserPassword("NEW_PASS");
        User updatedUser = userService.updateUser(existingUser);
        Assertions.assertEquals(existingUser.getUserName(),
                userService.findUserById(updatedUser.getUserId()).get().getUserName());
        Assertions.assertEquals(existingUser.getUserPassword(),
                userService.findUserById(updatedUser.getUserId()).get().getUserPassword());
    }

    @Test
    @DisplayName("testUpdateUserNotFound")
    void testUpdateUserNotFound() {
        User nonUser = new User(0L, "non_user", "pass", false);
        Assertions.assertThrows(UserNotFoundException.class,
                () -> {
                    userService.updateUser(nonUser);
                });
    }

    @Test
    @DisplayName("testDeleteById")
    void testDeleteById() {
        Long id = userService.findAllUsers().get(0).getUserId();
        Optional<User> deletedUser = userService.findUserById(id);
        // Test for presence of user to delete
        Assertions.assertTrue(deletedUser.isPresent());
        Assertions.assertThrows(UserNotFoundException.class,
                () -> {
                    userService.deleteUserById(deletedUser.get().getUserId());
                    userService.findUserById(deletedUser.get().getUserId());
                });

        Assertions.assertTrue(deletedUser.isPresent());
    }
}
