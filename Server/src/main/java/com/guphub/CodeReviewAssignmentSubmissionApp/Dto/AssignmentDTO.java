package com.guphub.CodeReviewAssignmentSubmissionApp.Dto;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;

public class AssignmentDTO {

    private Long id;
    private String status;
    private String githubUrl;
    private String branch;
    private String codeReviewVideoUrl;
    private User user; // Include UserDTO here

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCodeReviewVideoUrl() {
        return codeReviewVideoUrl;
    }

    public void setCodeReviewVideoUrl(String codeReviewVideoUrl) {
        this.codeReviewVideoUrl = codeReviewVideoUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AssignmentDTO{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", githubUrl='" + githubUrl + '\'' +
                ", branch='" + branch + '\'' +
                ", codeReviewVideoUrl='" + codeReviewVideoUrl + '\'' +
                ", user=" + user +
                '}';
    }
}
