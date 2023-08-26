package com.guphub.CodeReviewAssignmentSubmissionApp.Repository;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Assignment;
import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AssignmentRepository extends JpaRepository<Assignment,Long> {
  Set<Assignment> findByUser(User user);
}
