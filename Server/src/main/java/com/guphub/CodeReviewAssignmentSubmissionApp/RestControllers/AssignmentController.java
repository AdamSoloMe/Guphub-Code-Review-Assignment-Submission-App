package com.guphub.CodeReviewAssignmentSubmissionApp.RestControllers;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Assignment;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.AssignmentResponseDTO;
import com.guphub.CodeReviewAssignmentSubmissionApp.Service.AssignmentService;
import com.guphub.CodeReviewAssignmentSubmissionApp.enums.AssignmentStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth/assignments") // Add the base path
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping("/{assignmentNumber}")
    public ResponseEntity<?> createAssignments(@PathVariable int assignmentNumber) {
        try {
            // Get the authenticated user's information
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            AssignmentResponseDTO newAssignment = assignmentService.createAssignmentForUser(username, assignmentNumber);

            // Return success response or created assignment details
            return ResponseEntity.ok(newAssignment);
        } catch (Exception e) {
            // Handle error and return appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Assignment creation failed");
        }
    }


    @GetMapping("")
    public ResponseEntity<?> getAssignments() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

          Set<AssignmentResponseDTO> assignments = assignmentService.findByUsername(username);

            return ResponseEntity.ok(assignments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Assignment not found error: could not get Assignments");
        }
    }

    @GetMapping("{assignmentID}")
    public ResponseEntity<?> getAssignmentsByID(@PathVariable long assignmentID) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            Optional<Assignment> assignment = assignmentService.findByID(assignmentID);

            if (assignment.isPresent()) {
                AssignmentResponseDTO assignmentResponseDTO = assignmentService.convertToDTO(assignment.get());
                return ResponseEntity.ok(assignmentResponseDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Assignment not found error: could not find ID  ");
        }
    }

    @PutMapping("{assignmentID}")
    public ResponseEntity<?> updateAssignment(@PathVariable long assignmentID, @RequestBody AssignmentResponseDTO updatedAssignmentResponseDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            Optional<Assignment> existingAssignment = assignmentService.findByID(assignmentID);

            if (existingAssignment.isPresent()) {
                Assignment updatedAssignment = assignmentService.updateAssignment(existingAssignment.get(), updatedAssignmentResponseDTO);

                AssignmentResponseDTO assignmentResponseDTO = assignmentService.convertToDTO(updatedAssignment);
                return ResponseEntity.ok(assignmentResponseDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Assignment not found: could not be updated ");
        }
    }

    @DeleteMapping("{assignmentID}")
    public ResponseEntity<String> deleteAssignment(@PathVariable long assignmentID) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            Optional<Assignment> assignment = assignmentService.findByID(assignmentID);

            if (assignment.isPresent() && assignment.get().getUser().getUsername().equals(username)) {
                assignmentService.deleteAssignment(assignment.get());
                return ResponseEntity.ok("Assignment deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to delete this assignment");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteAllAssignments() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            Collection<AssignmentResponseDTO> assignments = assignmentService.findByUsername(username);

            for (AssignmentResponseDTO assignment : assignments) {
                assignmentService.deleteAssignmentByID(assignment.getId());
            }

            return ResponseEntity.ok("All assignments deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
    @GetMapping("/statuses")
    public ResponseEntity<?> getAllStatuses() {
        try {
            AssignmentStatusEnum[] statusEnums = AssignmentStatusEnum.values();
            return ResponseEntity.ok(statusEnums);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving statuses");
        }
    }

}
