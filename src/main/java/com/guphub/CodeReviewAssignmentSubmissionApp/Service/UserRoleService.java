package com.guphub.CodeReviewAssignmentSubmissionApp.Service;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Role;
import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;
import com.guphub.CodeReviewAssignmentSubmissionApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserRoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public void updateUserRolesByUsername(String username, Set<Role> roles) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setAuthorities(roles);
        userRepository.save(user);
    }

    // Other methods in the UserRoleService...

    // Method to update roles and refresh user details by username
    public void updateUserRolesAndRefreshByUsername(String username, Set<Role> roles) {
        updateUserRolesByUsername(username, roles);

        // Refresh the UserDetails for the updated user
        UserDetails updatedUserDetails = userDetailsService.loadUserByUsername(username);

        // Store the updated UserDetails in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        updatedUserDetails, updatedUserDetails.getPassword(), updatedUserDetails.getAuthorities())
        );
    }
}
