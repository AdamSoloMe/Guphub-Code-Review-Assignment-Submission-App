package com.guphub.CodeReviewAssignmentSubmissionApp.Dto;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;

public class LoginResponseDTO {
    private User user;
    private String jwt;

    public LoginResponseDTO(){
        super();
    }

    public LoginResponseDTO(User user, String jwt){
        this.user = user;
        this.jwt = jwt;
    }

    public User getUser(){
        return this.user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public String getJwt(){
        return this.jwt;
    }

    public void setJwt(String jwt){
        this.jwt = jwt;
    }

}