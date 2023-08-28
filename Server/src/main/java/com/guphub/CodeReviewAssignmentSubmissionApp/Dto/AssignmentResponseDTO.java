package com.guphub.CodeReviewAssignmentSubmissionApp.Dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;
import com.guphub.CodeReviewAssignmentSubmissionApp.Utils.AssignmentStatusEnumDeserializer;
import com.guphub.CodeReviewAssignmentSubmissionApp.enums.AssignmentEnum;
import com.guphub.CodeReviewAssignmentSubmissionApp.enums.AssignmentStatusEnum;

public class AssignmentResponseDTO {

    private Long id;

    @JsonDeserialize(using = AssignmentStatusEnumDeserializer.class)
    private AssignmentStatusEnum status;
    private String githubUrl;


    private Integer number; // New field for assignment number
    private String branch;
    private String codeReviewVideoUrl;
    private User user; // Include UserDTO here

    private String assignmentName;

    private AssignmentEnum assignmentType;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AssignmentStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AssignmentStatusEnum status) {
        this.status = status;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public AssignmentEnum getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(AssignmentEnum assignmentType) {
        this.assignmentType = assignmentType;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }


    @Override
    public String toString() {
        return "AssignmentResponseDTO{" +
                "id=" + id +
                ", status=" + status +
                ", githubUrl='" + githubUrl + '\'' +
                ", number=" + number +
                ", branch='" + branch + '\'' +
                ", codeReviewVideoUrl='" + codeReviewVideoUrl + '\'' +
                ", user=" + user +
                ", assignmentName='" + assignmentName + '\'' +
                ", assignmentType=" + assignmentType +
                '}';
    }
}
