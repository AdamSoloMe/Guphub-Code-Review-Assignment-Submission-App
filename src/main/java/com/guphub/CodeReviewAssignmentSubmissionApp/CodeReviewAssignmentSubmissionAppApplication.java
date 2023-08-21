package com.guphub.CodeReviewAssignmentSubmissionApp;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Role;
import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;
import com.guphub.CodeReviewAssignmentSubmissionApp.Repository.RoleRepository;
import com.guphub.CodeReviewAssignmentSubmissionApp.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CodeReviewAssignmentSubmissionAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeReviewAssignmentSubmissionAppApplication.class, args);
	}

//	@Bean
//	 CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
//		return args ->{
//			if(roleRepository.findRoleByAuthority("ADMIN").isPresent()) return;
//			Role adminRole = roleRepository.save(new Role("ADMIN"));
//			roleRepository.save(new Role("USER"));
//
//			Set<Role> roles = new HashSet<>();
//			roles.add(adminRole);
//
//			User admin = new User(1L, "admin", passwordEncode.encode("password"), roles);
//
//			userRepository.save(admin);
//		};
//	}

}
