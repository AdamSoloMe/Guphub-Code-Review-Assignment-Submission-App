package com.guphub.CodeReviewAssignmentSubmissionApp.Service;

import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.Role;
import com.guphub.CodeReviewAssignmentSubmissionApp.Datamodels.User;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.LoginResponseDTO;
import com.guphub.CodeReviewAssignmentSubmissionApp.Dto.UserDto;
import com.guphub.CodeReviewAssignmentSubmissionApp.Repository.RoleRepository;
import com.guphub.CodeReviewAssignmentSubmissionApp.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
    private TokenService tokenService;

    private Map<String, String> accessTokenMap = new ConcurrentHashMap<>(); // Use a map to store access tokens
    private Map<String, String> refreshTokenMap = new ConcurrentHashMap<>(); // Use a map to store refresh tokens

    public User registeredUser(UserDto userDto){
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        Role userRole = roleRepository.findRoleByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        User newUser = new User(userDto.getUsername(), encodedPassword, authorities);
        return userRepository.save(newUser);
    }
    public boolean isUserRegistered(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent();
    }


    public LoginResponseDTO loginUser(UserDto userDto) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
            );

            String username = userDto.getUsername();
            String accessToken;
            String refreshToken;

            if (accessTokenMap.containsKey(username)) {
                // If an access token already exists for the user, use it
                accessToken = accessTokenMap.get(username);
                refreshToken = refreshTokenMap.get(username);

                // Check if the refresh token is expired
                if (!tokenService.isRefreshTokenValid(refreshToken)) {
                    // Clear the expired tokens and generate new ones
                    accessTokenMap.remove(username);
                    refreshTokenMap.remove(username);
                    accessToken = tokenService.generateJwt(auth);
                    refreshToken = tokenService.refreshJwt(accessToken);
                    accessTokenMap.put(username, accessToken);
                    refreshTokenMap.put(username, refreshToken);
                }
//                 else if ( (tokenService.isRefreshTokenValid(refreshToken)))
//                {
//                    // Refresh the access token using the existing refresh token
//                    accessToken = tokenService.refreshJwt(refreshToken);
//                    accessTokenMap.put(username, accessToken);
//                }
            } else {
                // Otherwise, generate a new access token and refresh token
                accessToken = tokenService.generateJwt(auth);
                refreshToken = tokenService.refreshJwt(accessToken);
                accessTokenMap.put(username, accessToken);
                refreshTokenMap.put(username, refreshToken);
            }

            return new LoginResponseDTO(userRepository.findByUsername(username).get(), accessToken, refreshToken);

        } catch (AuthenticationException e) {
            return new LoginResponseDTO(null, "", "");
        }
    }

    public String refreshAccessToken(String refreshToken) {
        try {
            // Check if the refresh token is expired
            if (!tokenService.isRefreshTokenValid(refreshToken)) {
                return "token is expired and is not valid"; // Expired refresh token
            }

            return tokenService.refreshJwt(refreshToken);
        } catch (Exception e) {
            // Handle token refresh error
            return null;
        }
    }
}
