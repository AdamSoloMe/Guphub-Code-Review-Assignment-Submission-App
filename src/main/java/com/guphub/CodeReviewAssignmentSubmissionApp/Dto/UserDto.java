package com.guphub.CodeReviewAssignmentSubmissionApp.Dto;


import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;
    private String password;



}
