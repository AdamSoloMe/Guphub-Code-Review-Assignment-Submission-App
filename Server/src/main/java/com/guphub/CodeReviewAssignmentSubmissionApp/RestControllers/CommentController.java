package com.guphub.CodeReviewAssignmentSubmissionApp.RestControllers;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Comment;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.CommentDTO;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.CommentResponseDTO;
import com.guphub.CodeReviewAssignmentSubmissionApp.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/auth/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping//works, done
    public ResponseEntity<Comment> createComment(@RequestBody CommentDTO commentDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        commentDTO.setUsername(username);

        Comment createdComment = commentService.createComment(commentDTO);
        return ResponseEntity.ok(createdComment);
    }

    @GetMapping("/assignment/{assignmentId}")//works, done
    public ResponseEntity<Set<Comment>> getCommentsByAssignmentId(@PathVariable Long assignmentId) {
        Set<Comment> comments = commentService.getCommentsByAssignmentId(assignmentId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{commentId}")//works, done
    public ResponseEntity<CommentResponseDTO> getCommentById(@PathVariable Long commentId) {
        CommentResponseDTO commentResponseDTO = commentService.getCommentResponseById(commentId);
        return ResponseEntity.ok(commentResponseDTO);
    }

    @PutMapping("/{commentId}")//works, done
    public ResponseEntity<CommentResponseDTO> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentDTO commentDTO) {
        CommentResponseDTO updatedComment = commentService.updateCommentResponse(commentId, commentDTO);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{commentId}")//works
    public ResponseEntity<String> deleteCommentById(@PathVariable Long commentId) {
        commentService.deleteCommentById(commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }


    @DeleteMapping("/assignment/{assignmentId}")//works, done
    public ResponseEntity<String> deleteCommentsByAssignmentId(@PathVariable Long assignmentId) {
        commentService.deleteCommentsByAssignmentId(assignmentId);
        return ResponseEntity.ok("All Comments for this assiignment have been deleted successfully");
    }

    @PostMapping("/assignment/{assignmentId}/comments") //works
    public ResponseEntity<CommentResponseDTO> createAssignmentComment(
            @PathVariable Long assignmentId,
            @RequestBody CommentDTO commentDTO) {
        CommentResponseDTO createdComment = commentService.createAssignmentComment(assignmentId, commentDTO);
        return ResponseEntity.ok(createdComment);
    }

    @PutMapping("/assignment/{assignmentId}/comments/{commentId}")//works
    public ResponseEntity<CommentResponseDTO> updateAssignmentComment(
            @PathVariable Long assignmentId,
            @PathVariable Long commentId,
            @RequestBody CommentDTO commentDTO) {
        CommentResponseDTO updatedComment = commentService.updateAssignmentComment(assignmentId, commentId, commentDTO);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/assignment/{assignmentId}/comments/{commentId}")//works
    public ResponseEntity<String> deleteAssignmentComment(
            @PathVariable Long assignmentId,
            @PathVariable Long commentId) {
        commentService.deleteAssignmentComment(assignmentId, commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }

    @GetMapping("/assignment/{assignmentId}/comments") //works,done
    public ResponseEntity<Set<CommentResponseDTO>> getAssignmentComments(@PathVariable Long assignmentId) {
        Set<CommentResponseDTO> comments = commentService.getCommentsFromAssignmentId(assignmentId);
        return ResponseEntity.ok(comments);
    }
}
