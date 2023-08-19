package com.sarahweisser.winetrackerapi.services;

import com.sarahweisser.winetrackerapi.models.User;
import com.sarahweisser.winetrackerapi.repositories.UserJpaRepository;
import org.junit.jupiter.api.Assertions;
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

    @Test
    @DisplayName("Test processUserLogin")
    void testProcessUserLogin() {
        this.userList.add(user1);
        this.userList.add(user2);
        when(userJpaRepository.findUsersByUserName("aa")).thenReturn(Optional.of(user1));
        User existingUser = userService.processUserLogin(user1);
        Assertions.assertEquals(existingUser.getUserId(), user1.getUserId());
    }
}
