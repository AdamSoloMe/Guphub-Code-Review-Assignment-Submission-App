package com.guphub.CodeReviewAssignmentSubmissionApp.Service;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Role;
import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;
import com.guphub.CodeReviewAssignmentSubmissionApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepository userRepo;
    // this User Repository is used to retrieve user related data from the database for the Service Layer of my application
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //This method is used to load user specific data From the User Data Repository .
        System.out.println("User found");
        User userOptional = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));

        Set<Role> roles = (Set<Role>) userOptional.getAuthorities(); // Assuming userOptional.getAuthorities() returns Set<Role>

        Set<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                userOptional.getUsername(),
                userOptional.getPassword(),
                authorities
        );
//
//        if (!username.equals("Adam")) throw new UsernameNotFoundException("not Adam");
//        Set<Role> roles=new HashSet<>();
//        roles.add(new Role(1L,"USER"));
//
//        return new User(1L,"Adam",passwordEncoder.encode("password"),roles);
    }

}
