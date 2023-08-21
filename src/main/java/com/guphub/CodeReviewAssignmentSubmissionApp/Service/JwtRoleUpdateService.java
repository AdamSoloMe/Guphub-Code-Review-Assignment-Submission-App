package com.guphub.CodeReviewAssignmentSubmissionApp.Service;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Role;
import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;
import com.guphub.CodeReviewAssignmentSubmissionApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class JwtRoleUpdateService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    // Method to add a new role to a user's JWT
    public String addRoleToJwt(String jwtToken, Role newRoleToAdd) {
        String username = jwtService.extractUsername(jwtToken);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Set<Role> existingRoles = (Set<Role>) user.getAuthorities();
        existingRoles.add(newRoleToAdd);

        userRoleService.updateUserRolesAndRefreshByUsername(username, existingRoles);


        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtService.generateToken(userDetails);
    }
}