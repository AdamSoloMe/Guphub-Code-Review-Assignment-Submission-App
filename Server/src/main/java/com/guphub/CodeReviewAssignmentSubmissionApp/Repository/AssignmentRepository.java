package com.guphub.CodeReviewAssignmentSubmissionApp.Repository;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Assignment;
import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface AssignmentRepository extends JpaRepository<Assignment,Long> {
  Set<Assignment> findByUser(User user);




  @Query("SELECT a FROM Assignment a WHERE  a.status = 'SUBMITTED'")
  Set<Assignment> findByAdmin(User user);
}
