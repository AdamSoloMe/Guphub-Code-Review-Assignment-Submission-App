package com.guphub.CodeReviewAssignmentSubmissionApp.Service;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Assignment;
import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.AssignmentDTO;
import com.guphub.CodeReviewAssignmentSubmissionApp.Repository.AssignmentRepository;
import com.guphub.CodeReviewAssignmentSubmissionApp.Repository.UserRepository;
import com.guphub.CodeReviewAssignmentSubmissionApp.enums.AssignmentEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserRepository userRepository;

    public AssignmentDTO createAssignmentForUser(String username, int assignmentNumber) {
        Assignment assignment = new Assignment();
        assignment.setStatus("Needs to be Submitted");
        AssignmentEnum assignmentType = AssignmentEnum.getAssignmentEnumByNum(assignmentNumber);
        String assignmentName = AssignmentEnum.getAssignmentEnumByName(assignmentType.getAssignmentName()).getAssignmentName();



        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        assignment.setUser(user);

        assignment.setAssignmentType(assignmentType);
        assignment.setAssignmentName(assignmentName);

        Assignment savedAssignment = assignmentRepository.save(assignment);

        AssignmentDTO assignmentDTO = new AssignmentDTO();
        assignmentDTO.setId(savedAssignment.getId());
        assignmentDTO.setStatus(savedAssignment.getStatus());
        assignmentDTO.setGithubUrl(savedAssignment.getGithubUrl());
        assignmentDTO.setBranch(savedAssignment.getBranch());
        assignmentDTO.setCodeReviewVideoUrl(savedAssignment.getCodeReviewVideoUrl());
        assignmentDTO.setUser(savedAssignment.getUser()); // Set the user ID

        // Set assignment type based on assignment number
        //AssignmentEnum assignmentType = AssignmentEnum.getAssignmentEnumByNum(assignmentNumber);
        assignmentDTO.setAssignmentType(assignmentType);
        assignmentDTO.setAssignmentName(assignmentName);
        return assignmentDTO;
    }


    public Set<AssignmentDTO> findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Set<Assignment> assignments = assignmentRepository.findByUser(user);
        Set<AssignmentDTO> assignmentDTOs = new HashSet<>();

        if (assignments.isEmpty()) {
            throw new IllegalStateException("No assignments found for the user");
        }

        for (Assignment assignment : assignments) {
            AssignmentDTO assignmentDTO = new AssignmentDTO();
            assignmentDTO.setId(assignment.getId());
            assignmentDTO.setStatus(assignment.getStatus());
            assignmentDTO.setGithubUrl(assignment.getGithubUrl());
            assignmentDTO.setBranch(assignment.getBranch());
            assignmentDTO.setCodeReviewVideoUrl(assignment.getCodeReviewVideoUrl());
            assignmentDTO.setUser(assignment.getUser());
            assignmentDTO.setAssignmentType(assignment.getAssignmentType()); // Populate assignment type
            assignmentDTO.setAssignmentName(assignment.getAssignmentName()); // Populate assignment name


            assignmentDTOs.add(assignmentDTO);
        }

        return assignmentDTOs;
    }

    public Optional<Assignment> findByID(Long assignmentID){
        return assignmentRepository.findById(assignmentID);

    }

    public AssignmentDTO convertToDTO(Assignment assignment) {
        AssignmentDTO assignmentDTO = new AssignmentDTO();
        assignmentDTO.setId(assignment.getId());
        assignmentDTO.setStatus(assignment.getStatus());
        assignmentDTO.setGithubUrl(assignment.getGithubUrl());
        assignmentDTO.setBranch(assignment.getBranch());
        assignmentDTO.setCodeReviewVideoUrl(assignment.getCodeReviewVideoUrl());
        assignmentDTO.setUser(assignment.getUser());
        assignmentDTO.setAssignmentType(assignment.getAssignmentType()); // Populate assignment type
        assignmentDTO.setAssignmentName(assignment.getAssignmentName()); // Populate assignment name

        return assignmentDTO;
    }

    public Assignment updateAssignment(Assignment existingAssignment, AssignmentDTO updatedAssignmentDTO) {
        existingAssignment.setStatus(updatedAssignmentDTO.getStatus());
        existingAssignment.setGithubUrl(updatedAssignmentDTO.getGithubUrl());
        existingAssignment.setBranch(updatedAssignmentDTO.getBranch());
        existingAssignment.setCodeReviewVideoUrl(updatedAssignmentDTO.getCodeReviewVideoUrl());
        existingAssignment.setAssignmentType(updatedAssignmentDTO.getAssignmentType());
        existingAssignment.setAssignmentName(updatedAssignmentDTO.getAssignmentName());

        return assignmentRepository.save(existingAssignment);
    }

    public void deleteAssignment(Assignment assignment) {
        assignmentRepository.delete(assignment);
    }

    public void deleteAssignmentByID(Long assignmentID) {
        assignmentRepository.deleteById(assignmentID);
    }

}