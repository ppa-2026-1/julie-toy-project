package com.example.demo.transversal.filter;

import java.io.IOException;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.model.AuthService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {

    private static final Set<String> PUBLIC_POST_PATHS = Set.of(
            "/api/v1/users",
            "/api/v1/auth/login"
    );

    private final AuthService authService;

    public AuthFilter(AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();

        if (path.startsWith("/h2-console")) {
            return true;
        }

        return "POST".equalsIgnoreCase(request.getMethod()) && PUBLIC_POST_PATHS.contains(path);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write("Header Authorization deve usar Bearer token");
            return;
        }

        String token = authorization.substring("Bearer ".length()).trim();

        try {
            String handle = authService.validateTokenAndGetHandle(token);
            request.setAttribute("authenticatedUser", handle);
            filterChain.doFilter(request, response);
        } catch (IllegalArgumentException ex) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write(ex.getMessage());
        }
    }
}
