package com.guphub.CodeReviewAssignmentSubmissionApp.Service;

import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.CommentDTO;
import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Assignment;
import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Comment;
import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.CommentResponseDTO;
import com.guphub.CodeReviewAssignmentSubmissionApp.Repository.AssignmentRepository;
import com.guphub.CodeReviewAssignmentSubmissionApp.Repository.CommentRepository;
import com.guphub.CodeReviewAssignmentSubmissionApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    public Comment createComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setCommentText(commentDTO.getCommentText());

        User createdBy = userRepository.findByUsername(commentDTO.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        comment.setCreatedBy(createdBy);

        Assignment assignment = assignmentRepository.findById(commentDTO.getAssignmentId())
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));
        comment.setAssignment(assignment);

        comment.setDate(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    public Set<Comment> getCommentsByAssignmentId(Long assignmentId) {
        return commentRepository.findByAssignment_Id(assignmentId);
    }

    public Set<CommentResponseDTO> getCommentsFromAssignmentId(Long assignmentId) {
        Set<Comment> comments = commentRepository.findByAssignment_Id(assignmentId);
        return comments.stream()
                .map(comment -> {
                    CommentResponseDTO dto = new CommentResponseDTO();
                    dto.setId(comment.getId());
                    dto.setCommentText(comment.getCommentText());
                    dto.setUsername(comment.getCreatedBy().getUsername());
                    return dto;
                })
                .collect(Collectors.toSet());
    }




    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with ID: " + commentId));
    }

    public Comment updateComment(Long commentId, CommentDTO commentDTO) {
        Comment comment = getCommentById(commentId);
        comment.setCommentText(commentDTO.getCommentText());
        return commentRepository.save(comment);
    }

    public void deleteCommentById(Long commentId) {
        Comment comment = getCommentById(commentId);
        commentRepository.delete(comment);
    }

    public void deleteCommentsByAssignmentId(Long assignmentId) {
        commentRepository.deleteByAssignment_Id(assignmentId);
    }

    public CommentResponseDTO createAssignmentComment(Long assignmentId, CommentDTO commentDTO) {
        commentDTO.setAssignmentId(assignmentId);
        Comment createdComment = createComment(commentDTO);
        return new CommentResponseDTO(createdComment);
    }

    public CommentResponseDTO updateAssignmentComment(Long assignmentId, Long commentId, CommentDTO commentDTO) {
        Comment comment = getCommentById(commentId);
        comment.setCommentText(commentDTO.getCommentText());
        Comment updatedComment = commentRepository.save(comment);
        return new CommentResponseDTO(updatedComment);
    }

    public void deleteAssignmentComment(Long assignmentId, Long commentId) {
        deleteCommentById(commentId);
    }
    public CommentResponseDTO getCommentResponseById(Long commentId) {
        Comment comment = getCommentById(commentId);
        return new CommentResponseDTO(comment); // Assuming you have a constructor in CommentResponseDTO that accepts a Comment object
    }

    public CommentResponseDTO updateCommentResponse(Long commentId, CommentDTO commentDTO) {
        Comment comment = getCommentById(commentId);
        comment.setCommentText(commentDTO.getCommentText());
        Comment updatedComment = commentRepository.save(comment);
        return new CommentResponseDTO(updatedComment);
    }

}

