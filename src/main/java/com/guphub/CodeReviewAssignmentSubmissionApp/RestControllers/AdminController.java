package com.guphub.CodeReviewAssignmentSubmissionApp.RestControllers;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Role;
import com.guphub.CodeReviewAssignmentSubmissionApp.Service.JwtRoleUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private JwtRoleUpdateService jwtRoleUpdateService;

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')") // Restrict access to users with 'ADMIN' role
    public ResponseEntity<String> addAdminRole(@RequestHeader("Authorization") String jwtToken) {
        try {
            String newJwtToken = jwtRoleUpdateService.addRoleToJwt(jwtToken, new Role("ADMIN"));
            return ResponseEntity.ok(newJwtToken);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding role.");
        }
    }
}
