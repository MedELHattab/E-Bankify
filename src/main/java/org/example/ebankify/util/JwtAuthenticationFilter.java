package org.example.ebankify.util;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter implements Filter {

    private final CustomJwtUtil jwtUtil;

    @Autowired
    public JwtAuthenticationFilter(CustomJwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Check if the request URI starts with /users/
        String requestURI = httpRequest.getRequestURI();
        if (!requestURI.startsWith("/users")) {
            chain.doFilter(request, response); // Skip filter if not /users/*
            return;
        }

        // Proceed with token validation
        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String email = jwtUtil.extractEmail(token);

            if (jwtUtil.validateToken(token, email)) {
                chain.doFilter(request, response); // Continue if valid
                return;
            }
        }

        // Unauthorized if token is invalid or missing
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or missing token");
    }


    @Override
    public void destroy() {}
}
