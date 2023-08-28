package com.guphub.CodeReviewAssignmentSubmissionApp.Dto;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;

public class LoginResponseDTO {
    private String refresh_token;
    private User user;
    private String access_token;

    public LoginResponseDTO(){
        super();
    }

    public LoginResponseDTO(User user, String accessToken, String refreshToken) {
        this.user = user;
        this.access_token = accessToken;
        this.refresh_token = refreshToken;
    }

    public String getRefresh_token() {
        return this.refresh_token;
    }

    public void setRefresh_token(String refreshToken) {
        this.refresh_token = refreshToken;
    }


    public User getUser(){
        return this.user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public String getAccess_token(){
        return this.access_token;
    }

    public void setAccess_token(String jwt){
        this.access_token = jwt;
    }

}