package com.guphub.CodeReviewAssignmentSubmissionApp.Service;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Assignment;
import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.AssignmentResponseDTO;
import com.guphub.CodeReviewAssignmentSubmissionApp.Repository.AssignmentRepository;
import com.guphub.CodeReviewAssignmentSubmissionApp.Repository.UserRepository;
import com.guphub.CodeReviewAssignmentSubmissionApp.enums.AssignmentEnum;
import com.guphub.CodeReviewAssignmentSubmissionApp.enums.AssignmentStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserRepository userRepository;

    public AssignmentResponseDTO createAssignmentForUser(String username, int assignmentNumber) {
        Assignment assignment = new Assignment();
        assignment.setStatus(AssignmentStatusEnum.PENDING_SUBMISSION);
        AssignmentEnum assignmentType = AssignmentEnum.getAssignmentEnumByNum(assignmentNumber);
        String assignmentName = AssignmentEnum.getAssignmentEnumByName(assignmentType.getAssignmentName()).getAssignmentName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        assignment.setUser(user);

        assignment.setAssignmentType(assignmentType);
        assignment.setAssignmentName(assignmentName);

        Integer nextAssignmentToSubmit = findNextAssignmentToSubmit(user);
        assignment.setNumber(nextAssignmentToSubmit);

        Assignment savedAssignment = assignmentRepository.save(assignment);

        AssignmentResponseDTO assignmentResponseDTO = new AssignmentResponseDTO();
        assignmentResponseDTO.setId(savedAssignment.getId());
        assignmentResponseDTO.setStatus(savedAssignment.getStatus());
        assignmentResponseDTO.setGithubUrl(savedAssignment.getGithubUrl());
        assignmentResponseDTO.setBranch(savedAssignment.getBranch());
        assignmentResponseDTO.setCodeReviewVideoUrl(savedAssignment.getCodeReviewVideoUrl());
        assignmentResponseDTO.setUser(savedAssignment.getUser());
        assignmentResponseDTO.setAssignmentType(assignmentType);
        assignmentResponseDTO.setAssignmentName(assignmentName);
        assignmentResponseDTO.setNumber(nextAssignmentToSubmit); // Set the assignment number

        return assignmentResponseDTO;
    }


    private Integer findNextAssignmentToSubmit(User user) {
        Set<Assignment> assignmentsByUser = assignmentRepository.findByUser(user);
        if (assignmentsByUser == null) {
            return 1;
        }
        Optional<Integer> nextAssignmentNumOpt = assignmentsByUser.stream().sorted((a1, a2) -> {
            if (a1.getNumber() == null)
                return 1;
            if (a2.getNumber() == null)
                return 1;
            return a2.getNumber().compareTo(a1.getNumber());
        }).map(assignment -> {
            if (assignment.getNumber() == null)
                return 1;
            return assignment.getNumber() + 1;
        }).findFirst();
        return nextAssignmentNumOpt.orElse(1);
    }
    public Set<AssignmentResponseDTO> findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Set<Assignment> assignments;

        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));

        if (isAdmin) {
            // If the user has the admin role, retrieve all assignments
            assignments =  assignmentRepository.findByAdmin(user);
        } else {
            // If the user has only the user role, retrieve assignments specific to that user
            assignments = assignmentRepository.findByUser(user);
        }

        Set<AssignmentResponseDTO> assignmentResponseDTOS = new HashSet<>();

        if (assignments.isEmpty()) {
            throw new IllegalStateException("No assignments found for the user");
        }

        for (Assignment assignment : assignments) {
            AssignmentResponseDTO assignmentResponseDTO = new AssignmentResponseDTO();
            assignmentResponseDTO.setId(assignment.getId());
            assignmentResponseDTO.setStatus(assignment.getStatus());
            assignmentResponseDTO.setGithubUrl(assignment.getGithubUrl());
            assignmentResponseDTO.setBranch(assignment.getBranch());
            assignmentResponseDTO.setCodeReviewVideoUrl(assignment.getCodeReviewVideoUrl());
            assignmentResponseDTO.setUser(assignment.getUser());
            assignmentResponseDTO.setAssignmentType(assignment.getAssignmentType()); // Populate assignment type
            assignmentResponseDTO.setAssignmentName(assignment.getAssignmentName()); // Populate assignment name
            assignmentResponseDTO.setNumber(assignment.getNumber());

            assignmentResponseDTOS.add(assignmentResponseDTO);
        }

        return assignmentResponseDTOS;
    }




//    public Set<AssignmentResponseDTO> findByUsername(String username) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//
//        Set<Assignment> assignments = assignmentRepository.findByUser(user);
//        Set<AssignmentResponseDTO> assignmentResponseDTOS = new HashSet<>();
//
//        if (assignments.isEmpty()) {
//            throw new IllegalStateException("No assignments found for the user");
//        }
//
//        for (Assignment assignment : assignments) {
//            AssignmentResponseDTO assignmentResponseDTO = new AssignmentResponseDTO();
//            assignmentResponseDTO.setId(assignment.getId());
//            assignmentResponseDTO.setStatus(assignment.getStatus());
//            assignmentResponseDTO.setGithubUrl(assignment.getGithubUrl());
//            assignmentResponseDTO.setBranch(assignment.getBranch());
//            assignmentResponseDTO.setCodeReviewVideoUrl(assignment.getCodeReviewVideoUrl());
//            assignmentResponseDTO.setUser(assignment.getUser());
//            assignmentResponseDTO.setAssignmentType(assignment.getAssignmentType()); // Populate assignment type
//            assignmentResponseDTO.setAssignmentName(assignment.getAssignmentName()); // Populate assignment name
//            assignmentResponseDTO.setNumber(assignment.getNumber());
//
//
//            assignmentResponseDTOS.add(assignmentResponseDTO);
//        }
//
//        return assignmentResponseDTOS;
//    }

    public Optional<Assignment> findByID(Long assignmentID){
        return assignmentRepository.findById(assignmentID);

    }

    public AssignmentResponseDTO convertToDTO(Assignment assignment) {
        AssignmentResponseDTO assignmentResponseDTO = new AssignmentResponseDTO();
        assignmentResponseDTO.setId(assignment.getId());
        assignmentResponseDTO.setStatus(assignment.getStatus());
        assignmentResponseDTO.setGithubUrl(assignment.getGithubUrl());
        assignmentResponseDTO.setBranch(assignment.getBranch());
        assignmentResponseDTO.setCodeReviewVideoUrl(assignment.getCodeReviewVideoUrl());
        assignmentResponseDTO.setUser(assignment.getUser());
        assignmentResponseDTO.setAssignmentType(assignment.getAssignmentType()); // Populate assignment type
        assignmentResponseDTO.setAssignmentName(assignment.getAssignmentName()); // Populate assignment name

        return assignmentResponseDTO;
    }

    public Assignment updateAssignment(Assignment existingAssignment, AssignmentResponseDTO updatedAssignmentResponseDTO) {
        String newStatusString = updatedAssignmentResponseDTO.getStatus().getStatus();
        AssignmentStatusEnum newStatusEnum = AssignmentStatusEnum.fromString(newStatusString);

        existingAssignment.setStatus(newStatusEnum);
        String newAssignmentName = updatedAssignmentResponseDTO.getAssignmentName();
        AssignmentEnum newAssignmentType = updatedAssignmentResponseDTO.getAssignmentType();

        if (newAssignmentName == null || newAssignmentType == null) {
            throw new IllegalArgumentException("Assignment name and type cannot be null");
        }
        existingAssignment.setGithubUrl(updatedAssignmentResponseDTO.getGithubUrl());
        existingAssignment.setBranch(updatedAssignmentResponseDTO.getBranch());
        existingAssignment.setCodeReviewVideoUrl(updatedAssignmentResponseDTO.getCodeReviewVideoUrl());
        existingAssignment.setAssignmentType(updatedAssignmentResponseDTO.getAssignmentType());
        existingAssignment.setAssignmentName(updatedAssignmentResponseDTO.getAssignmentName());
        existingAssignment.setNumber(updatedAssignmentResponseDTO.getNumber());

        return assignmentRepository.save(existingAssignment);
    }

    public void deleteAssignment(Assignment assignment) {
        assignmentRepository.delete(assignment);
    }

    public void deleteAssignmentByID(Long assignmentID) {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentID);

        if (assignment.isPresent()) {
            assignmentRepository.delete(assignment.get());
        } else {
            throw new IllegalArgumentException("Assignment not found with ID: " + assignmentID);
        }
    }


}