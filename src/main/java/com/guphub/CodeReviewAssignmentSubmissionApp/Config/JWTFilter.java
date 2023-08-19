//package com.guphub.CodeReviewAssignmentSubmissionApp.Config;
//
//
//import com.guphub.CodeReviewAssignmentSubmissionApp.Repository.UserRepository;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
////this  class is used to handle the JWT token security filter layer on top of Spring security for the application
//@Component
//public class JWTFilter extends OncePerRequestFilter {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        // Get authorization header and validate
//        final String authheader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (authheader == null || !authheader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        final String token = authheader.split(" ")[1].trim();
//        // Get user identity and set it on the spring security context
//        UserDetails userDetails = userRepository.findbyUsername(jwtUtil.getUsernameFromToken(token))
//                .orElse(null);
//
//        // Get jwt token and validate
//
//        if (!jwtUtil.validateToken(token,userDetails)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//
//
//        UsernamePasswordAuthenticationToken
//                authentication = new UsernamePasswordAuthenticationToken(
//                userDetails, null,
//                userDetails == null ?
//                        List.of() : userDetails.getAuthorities()
//        );
//
//        authentication.setDetails(
//                new WebAuthenticationDetailsSource().buildDetails(request)
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        filterChain.doFilter(request, response);
//    }
//
//}
