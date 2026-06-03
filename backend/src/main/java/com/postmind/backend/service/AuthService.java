package com.postmind.backend.service;


import com.postmind.backend.dto.AuthResponse;
import com.postmind.backend.dto.LoginRequest;
import com.postmind.backend.dto.RegisterRequest;
import com.postmind.backend.model.User;
import com.postmind.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
//     DI UserRepository automatically
    private final UserRepository userRepository;

//    Used for password hashing
//    private final PasswordEncoder passwordEncoder;

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

}
