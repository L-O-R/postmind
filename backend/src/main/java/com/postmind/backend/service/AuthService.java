package com.postmind.backend.service;


import com.postmind.backend.dto.AuthResponse;
import com.postmind.backend.dto.LoginRequest;
import com.postmind.backend.dto.RegisterRequest;
import com.postmind.backend.model.User;
import com.postmind.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService {
//     DI UserRepository automatically
    private final UserRepository userRepository;
    private final JwtService jwtService;

//    Used for password hashing
    private final PasswordEncoder passwordEncoder;

//    Register new User
    public AuthResponse register(RegisterRequest registerRequest) {
//        check if email already exists
        if(userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email Already Exists");
        }

//        check username already exists
        if(userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Username Already Exists");
        }

//        create user Object
        User user = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(registerRequest.getPassword())
                .build();
        userRepository.save(user);

        return AuthResponse.builder().message("Register Successful").build();
    }


    public String login(LoginRequest request) {
//        null
//        java datatype
//        User => null\
        // User != null
//        datatype
//         email / user exist or not
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new RuntimeException("User not found"));

//        passowrd validation
//        boolean isPasswordValid = passwordEncoder.matches(request.getPassword(), user.getPassword());
        boolean isPasswordValid = user.getPassword().equals(request.getPassword());

        if (!isPasswordValid) {
            throw new RuntimeException("Invalid Password");
        }

        return jwtService.generateToken(user.getEmail());

    }
}
