package com.guphub.CodeReviewAssignmentSubmissionApp.RestControllers;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.LoginResponseDTO;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.RefreshRequestDTO;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.UserDto;
import com.guphub.CodeReviewAssignmentSubmissionApp.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> registeredUser(@RequestBody UserDto body) {
        try {
            User registeredUser = authenticationService.registeredUser(body);
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }
//

    @GetMapping("/check-registration")
    public ResponseEntity<?> checkUserRegistration() {
        try {
            // Get the authenticated user's username from the security context
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            System.out.println("username found "+username);

            boolean isRegistered = authenticationService.isUserRegistered(username);
            if (isRegistered) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.ok(false);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error checking registration status");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto body) {
        try {
            LoginResponseDTO response = authenticationService.loginUser(body);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login failed: " + e.getMessage());
        }
    }
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestBody RefreshRequestDTO refreshRequest) {
        try {
            String newAccessToken = authenticationService.refreshAccessToken(refreshRequest.getRefresh_token());
            if (newAccessToken != null) {
                return ResponseEntity.ok(newAccessToken);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired refresh token");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token refresh failed: " + e.getMessage());
        }
    }
}

