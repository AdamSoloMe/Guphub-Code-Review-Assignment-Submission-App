package com.guphub.CodeReviewAssignmentSubmissionApp.Repository;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Role;
import com.guphub.CodeReviewAssignmentSubmissionApp.enums.AuthorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByAuthority(AuthorityEnum authority);
}
