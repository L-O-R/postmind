package com.postmind.backend.service;


import com.postmind.backend.model.User;
import com.postmind.backend.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

// request => trigger controller => function, validtion => controller

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {



    private final JwtService jwtService;
    private final UserRepository userRepository;



//    private , public , protect
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        // read cookie
        // get token
        // validate token
        // load user
        // user data => access
        // object authentication
        // user => role, => authentication info
        // SecurityContext => globally spring
        // continue request

        String jwt = null;
        System.out.println("jwt Hitted");
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    jwt = cookie.getValue();

                    break;
                }
            }
        }
        System.out.println("jwt:"+jwt);
        if(jwt != null){
            try{
                String email = jwtService.extractEmail(jwt);
                System.out.println(email);
                User user = userRepository.findByEmail(email).orElse(null);

                if (user !=  null && jwtService.isTokenValid(jwt, user.getEmail())){
//                     authorization => role
                    // token => email
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            Collections.emptyList()
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }catch (Exception ignored){

            }
        }

        filterChain.doFilter(request, response);



    }

}


// protect => class not from instance