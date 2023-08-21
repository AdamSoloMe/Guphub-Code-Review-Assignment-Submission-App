package com.guphub.CodeReviewAssignmentSubmissionApp.Service;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Role;
import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.LoginResponseDTO;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.RegisterResponse;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.UserDto;
import com.guphub.CodeReviewAssignmentSubmissionApp.Repository.RoleRepository;
import com.guphub.CodeReviewAssignmentSubmissionApp.Repository.UserRepository;
import com.guphub.CodeReviewAssignmentSubmissionApp.enums.AuthorityEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService tokenService;


    public RegisterResponse registeredUser(UserDto userDto){
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        Role userRole = roleRepository.findRoleByAuthority(AuthorityEnum.ROLE_STUDENT);

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        User newUser = User.builder()// Set cohort start date as appropriate
                .username(userDto.getUsername())
                .password(encodedPassword)
                .authorities(authorities)
                .build();
        userRepository.save(newUser);
        String jwtToken =tokenService.generateToken(newUser);
        return RegisterResponse
                .builder()
                .jwtToken(jwtToken)
                .build();

    }


    public LoginResponseDTO loginUser(UserDto userDto){

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDto.getUsername(),
                            userDto.getPassword()
                    )
            );

            String jwtToken =tokenService.generateToken((UserDetails) auth);

            return new LoginResponseDTO(userRepository.findByUsername(userDto.getUsername()).get(), jwtToken);

        } catch(AuthenticationException e){
            return new LoginResponseDTO(null, "");
        }
    }
}
