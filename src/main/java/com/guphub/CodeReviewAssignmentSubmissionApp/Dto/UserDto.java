package com.guphub.CodeReviewAssignmentSubmissionApp.Dto;

public class UserDto {

    private String username;
    private String password;


    public UserDto(String username, String password) {

        super();
        this.username = username;
        this.password = password;
    }

    public UserDto() {
        super();
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + this.username + '\'' +
                ", password='" + this.password + '\'' +
                '}';
    }
}
