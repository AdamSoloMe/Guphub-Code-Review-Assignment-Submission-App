package com.guphub.CodeReviewAssignmentSubmissionApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private JwtDecoder jwtDecoder;

    public String generateJwt(Authentication auth) {
        Instant now = Instant.now();

        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet.Builder claimsBuilder = JwtClaimsSet.builder()
                .issuer("Server")
                .issuedAt(now)
                .subject(auth.getName())
                .claim("roles", scope)
                .expiresAt(now.plus(15, ChronoUnit.MINUTES));

        // Check if the user has the ADMIN role and add it to the authorities
        if (scope.contains("ADMIN")) {
            claimsBuilder.claim("isAdmin", true);
        }

        JwtClaimsSet claims = claimsBuilder.build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    public String refreshJwt(String refreshToken) {
        try {
            Jwt decodedRefreshToken = jwtDecoder.decode(refreshToken);
            Instant now = Instant.now();

            String roles = decodedRefreshToken.getClaimAsString("roles");
            boolean isAdmin = roles.contains("ADMIN");

            JwtClaimsSet refreshedClaims = JwtClaimsSet.builder()
                    .issuer(decodedRefreshToken.getClaimAsString("iss"))
                    .issuedAt(now)
                    .subject(decodedRefreshToken.getSubject())
                    .claim("roles", roles)
                    .claim("isAdmin", isAdmin) // Add isAdmin claim
                    .expiresAt(now.plus(7, ChronoUnit.DAYS)) // Refresh token's expiration time
                    .build();

            return jwtEncoder.encode(JwtEncoderParameters.from(refreshedClaims)).getTokenValue();
        } catch (JwtException e) {
            // Handle token decoding or refresh error
            return e.getMessage();
        }
    }



    public boolean isRefreshTokenValid(String refreshToken) {
        try {
            Jwt decodedRefreshToken = jwtDecoder.decode(refreshToken);
            Instant expirationTime = decodedRefreshToken.getExpiresAt();
            Instant now = Instant.now();

            // Compare the expiration time with the current time
            return !expirationTime.isBefore(now);
        } catch (JwtException e) {
            // Handle token decoding error
            return false;
        }


    }
}