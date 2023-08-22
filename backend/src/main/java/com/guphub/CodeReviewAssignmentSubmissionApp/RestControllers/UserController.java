package com.guphub.CodeReviewAssignmentSubmissionApp.RestControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
@GetMapping("/")
    public String helloUserController(){
        return "user acccess level";
    }
}
