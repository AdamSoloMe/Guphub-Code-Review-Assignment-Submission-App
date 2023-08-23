package com.guphub.CodeReviewAssignmentSubmissionApp.Dto;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;

public class LoginResponseDTO {
    private User user;
    private String access_token;

    public LoginResponseDTO(){
        super();
    }

    public LoginResponseDTO(User user, String access_token){
        this.user = user;
        this.access_token = access_token;
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