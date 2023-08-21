package com.guphub.CodeReviewAssignmentSubmissionApp.Dto;

import lombok.Builder;
import lombok.Data;
@Builder
public class RegisterResponse {

    private  String jwtToken;

    public RegisterResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public RegisterResponse() {
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
