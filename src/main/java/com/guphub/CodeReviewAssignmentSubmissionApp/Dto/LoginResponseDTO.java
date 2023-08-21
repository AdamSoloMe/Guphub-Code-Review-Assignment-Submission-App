package com.guphub.CodeReviewAssignmentSubmissionApp.Dto;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;


public class LoginResponseDTO {
    private String username;
    private String password;

    public LoginResponseDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginResponseDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}