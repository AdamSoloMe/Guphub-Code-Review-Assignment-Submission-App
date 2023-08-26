package com.guphub.CodeReviewAssignmentSubmissionApp.RestControllers;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Assignment;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.AssignmentDTO;
import com.guphub.CodeReviewAssignmentSubmissionApp.Service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

            AssignmentDTO newAssignment = assignmentService.createAssignmentForUser(username, assignmentNumber);

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

            Set<AssignmentDTO> assignments = assignmentService.findByUsername(username);

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
                AssignmentDTO assignmentDTO = assignmentService.convertToDTO(assignment.get());
                return ResponseEntity.ok(assignmentDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Assignment not found error: could not find ID  ");
        }
    }

    @PutMapping("{assignmentID}")
    public ResponseEntity<?> updateAssignment(@PathVariable long assignmentID, @RequestBody AssignmentDTO updatedAssignmentDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            Optional<Assignment> existingAssignment = assignmentService.findByID(assignmentID);

            if (existingAssignment.isPresent()) {
                Assignment updatedAssignment = assignmentService.updateAssignment(existingAssignment.get(), updatedAssignmentDTO);

                AssignmentDTO assignmentDTO = assignmentService.convertToDTO(updatedAssignment);
                return ResponseEntity.ok(assignmentDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Assignment not found: could be updated ");
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

            Set<AssignmentDTO> assignments = assignmentService.findByUsername(username);

            for (AssignmentDTO assignment : assignments) {
                assignmentService.deleteAssignmentByID(assignment.getId());
            }

            return ResponseEntity.ok("All assignments deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }


}
