package com.guphub.CodeReviewAssignmentSubmissionApp.Repository;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.assignment.id = :assignmentId")
    Set<Comment> findByAssignment_Id(Long assignmentId);


    @Transactional
    @Modifying
    @Query("DELETE FROM Comment c WHERE c.assignment.id = :assignmentId")
    void deleteByAssignment_Id(Long assignmentId);


}
