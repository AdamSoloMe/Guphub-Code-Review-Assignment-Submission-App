package com.guphub.CodeReviewAssignmentSubmissionApp.Repository;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
//this interface is part of The applications data access layer and is used to retrieve user data from the database
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
}
