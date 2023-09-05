package com.guphub.CodeReviewAssignmentSubmissionApp.Dto;

public class AccessTokenResponseDTO {
    private String accessToken;

    public AccessTokenResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
