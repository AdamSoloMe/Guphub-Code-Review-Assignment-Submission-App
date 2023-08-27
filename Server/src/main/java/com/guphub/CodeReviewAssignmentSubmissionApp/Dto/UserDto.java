package com.guphub.CodeReviewAssignmentSubmissionApp.Dto;


public class UserDto {

    private String username;
    private String password;
    private boolean isAdmin; // New field to indicate if the user should be registered as an admin

    public UserDto(String username, String password, boolean isAdmin) {
        super();
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + this.username + '\'' +
                ", password='" + this.password + '\'' +
                ", isAdmin=" + this.isAdmin + // Include isAdmin field in the toString() method
                '}';
    }
}

