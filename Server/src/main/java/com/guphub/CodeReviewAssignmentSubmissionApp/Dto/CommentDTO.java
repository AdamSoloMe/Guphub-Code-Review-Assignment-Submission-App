package com.guphub.CodeReviewAssignmentSubmissionApp.Dto;

public class CommentDTO {

    private Long assignmentId;
    private String username;
    private String commentText;

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "assignmentId=" + assignmentId +
                ", username='" + username + '\'' +
                ", commentText='" + commentText + '\'' +
                '}';
    }
}
