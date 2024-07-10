package com.uber.service.auth.filters;

import com.uber.service.auth.service.JwtService;
import com.uber.service.auth.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public JwtAuthFilter(JwtService jwtService, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.jwtService = jwtService;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                if(cookie.getName().equals("JwtToken")){
                    token = cookie.getValue();
                }
            }
        }

        if(token == null){
            // user has not provided any JwtToken hence request should not go forward
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        System.out.println("Incoming Token: " + token);
        String email = jwtService.getClaimFromToken(token, Claims::getSubject);
        System.out.println("Incoming email: " + email);

        if(email != null){
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email);
            if(jwtService.isValidJwt(token, userDetails.getUsername())){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        System.out.println("Forwarding Request: " + request.getRequestURI());
        filterChain.doFilter(request, response);
    }

    private final RequestMatcher uriMatcher = new AntPathRequestMatcher("/api/v1/auth/validate", "GET");

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        RequestMatcher requestMatcher = new NegatedRequestMatcher(uriMatcher);
        return requestMatcher.matches(request);
    }
}
