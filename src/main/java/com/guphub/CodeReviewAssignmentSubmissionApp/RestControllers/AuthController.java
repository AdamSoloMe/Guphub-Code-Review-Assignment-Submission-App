package com.guphub.CodeReviewAssignmentSubmissionApp.RestControllers;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.AuthResponseDTO;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.LoginResponseDTO;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.RegisterResponse;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.UserDto;
import com.guphub.CodeReviewAssignmentSubmissionApp.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registeredUser(@RequestBody UserDto body){
        return  ResponseEntity.ok(authenticationService.registeredUser(body));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginUser(@RequestBody LoginResponseDTO body){
        return  ResponseEntity.ok(authenticationService.loginUser(body));
    }

//    @PostMapping("/refresh")
//    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody UserDto body){
//        return  ResponseEntity.ok(authenticationService.loginUser(body));
//    }


}
