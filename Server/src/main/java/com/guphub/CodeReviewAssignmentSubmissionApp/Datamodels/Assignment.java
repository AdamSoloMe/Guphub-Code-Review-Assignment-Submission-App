package com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels;


import com.guphub.CodeReviewAssignmentSubmissionApp.enums.AssignmentEnum;
import jakarta.persistence.*;

@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String githubUrl;

    private String branch;
    private String codeReviewVideoUrl;

    private String assignmentName;
    @ManyToOne(optional = false)
    private User user;

    @Enumerated(EnumType.STRING) // Store enum as string in the database
    private AssignmentEnum assignmentType; // Include assignment type based on assignment number

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
        return "Assignment{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", githubUrl='" + githubUrl + '\'' +
                ", branch='" + branch + '\'' +
                ", codeReviewVideoUrl='" + codeReviewVideoUrl + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", user=" + user +
                ", assignmentType=" + assignmentType +
                '}';
    }
}
