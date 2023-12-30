package com.rahimov.lawfirmmanagementdeploy.config;



import com.rahimov.lawfirmmanagementdeploy.service.Impl.JWTServiceImpl;
import com.rahimov.lawfirmmanagementdeploy.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JWTServiceImpl jwtServiceImpl;

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String jwt;
        String email;

        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt= authHeader.substring(7);
        email = jwtServiceImpl.extractUsername(jwt);

        if ( !(email==null) && SecurityContextHolder.getContext().getAuthentication() ==null){

            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(email);

            if (jwtServiceImpl.isTokenValid(jwt,userDetails)){
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                UsernamePasswordAuthenticationToken authToken=
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());


                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                securityContext.setAuthentication(authToken);

                SecurityContextHolder.setContext(securityContext);
            }
        }
filterChain.doFilter(request,response);
    }
}