package com.guphub.CodeReviewAssignmentSubmissionApp;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Role;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.UserDto;
import com.guphub.CodeReviewAssignmentSubmissionApp.Service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CodeReviewAssignmentSubmissionAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeReviewAssignmentSubmissionAppApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner createDefaultUser(AuthenticationService authenticationService) {
//		return args -> {
//			// Create a default user
//			UserDto defaultUser = UserDto.builder()
//					.username("defaultUser")
//					.password("defaultPassword")
//					.role(Role.builder().authority(AuthorityEnum.ADMIN).build()) // Change this to the appropriate default role
//					.build();
//
//			System.out.println("Admin token: "+authenticationService.registeredUser(defaultUser).getAccessToken());
//		};
//	}

}
