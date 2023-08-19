package com.guphub.CodeReviewAssignmentSubmissionApp.RestControllers;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.LoginResponseDTO;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.UserDto;
import com.guphub.CodeReviewAssignmentSubmissionApp.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public User registeredUser(@RequestBody UserDto body){
        return  authenticationService.registeredUser(body);
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody UserDto body){
        return  authenticationService.loginUser(body);
    }


}
