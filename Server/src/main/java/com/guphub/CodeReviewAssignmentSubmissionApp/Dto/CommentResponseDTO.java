package com.guphub.CodeReviewAssignmentSubmissionApp.Dto;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Comment;

public class CommentResponseDTO {

    private Long id;
    private String commentText;
    private String username;



    public CommentResponseDTO(Comment comment) {
        this.id = comment.getId();
        this.commentText = comment.getCommentText();
        this.username = comment.getCreatedBy().getUsername();
    }

    public CommentResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString() {
        return "CommentResponseDTO{" +
                "id=" + id +
                ", commentText='" + commentText + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}