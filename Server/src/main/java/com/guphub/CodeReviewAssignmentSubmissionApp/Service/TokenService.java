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

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .subject(auth.getName())
                .claim("roles", scope)
                .expiresAt(now.plus(10, ChronoUnit.SECONDS))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String refreshJwt(String refreshToken) {
        try {
            Jwt decodedRefreshToken = jwtDecoder.decode(refreshToken);
            Instant now = Instant.now();

            JwtClaimsSet refreshedClaims = JwtClaimsSet.builder()
                    .issuer(decodedRefreshToken.getClaimAsString("iss"))
                    .issuedAt(now)
                    .subject(decodedRefreshToken.getSubject())
                    .claim("roles", decodedRefreshToken.getClaimAsString("roles"))
                    .expiresAt(now.plus(7, ChronoUnit.DAYS)) // Refresh token's expiration time
                    .build();

            return jwtEncoder.encode(JwtEncoderParameters.from(refreshedClaims)).getTokenValue();
        } catch (JwtException e) {
            // Handle token decoding or refresh error
            return null;
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