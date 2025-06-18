package com.bartr.filter;


import com.bartr.security.JwtUtil;
import com.bartr.service.impl.UserAuthServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserAuthServiceImpl userAuthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        try {

            // Validate presence of Authorization header
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                log.info("Missing or malformed Authorization header.");
                filterChain.doFilter(request, response);
                return;
            }



            // Extract JWT token
            String jwt = authorizationHeader.substring(7);
            String username = jwtUtil.extractUsername(jwt);

            if (username == null || SecurityContextHolder.getContext().getAuthentication() != null) {
                log.warn("Invalid JWT token or user already authenticated.");
                filterChain.doFilter(request, response);
                return;
            }

            log.info("Validating JWT token for user: {}", username);
            UserDetails userDetails = userAuthService.loadUserByUsername(username);

            // Validate JWT token
            if (!jwtUtil.validateToken(jwt)) {
                log.error("JWT validation failed for user: {}", username);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired JWT token.");
                return;
            }

            // Set authentication context
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            log.info("JWT authentication successful for user: {}", username);

        } catch (ServletException e) {
            log.error("Exception occurred during JWT authentication: {}", e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal authentication error.");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
