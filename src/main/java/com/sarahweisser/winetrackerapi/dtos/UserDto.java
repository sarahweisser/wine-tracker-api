package com.sarahweisser.winetrackerapi.dtos;

import com.sarahweisser.winetrackerapi.models.User;

public class UserDto {
    private User user;
    private String exceptionMessage;

    public UserDto(User user, String exceptionMessage) {
        this.user = user;
        this.exceptionMessage = exceptionMessage;
    }

    public UserDto() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
