package com.team47.udemybackend.auth;

import com.team47.udemybackend.config.JwtService;
import com.team47.udemybackend.dto.AuthenticationRequest;
import com.team47.udemybackend.dto.AuthenticationRespone;
import com.team47.udemybackend.dto.LoginRequest;
import com.team47.udemybackend.dto.RegisterRequest;
import com.team47.udemybackend.dto.response.BaseResponse;
import com.team47.udemybackend.exception.UdemyRuntimeException;
import com.team47.udemybackend.user.UserRepository;
import com.team47.udemybackend.user.Role;
import com.team47.udemybackend.user.User;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Builder
@Transactional
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public BaseResponse register(RegisterRequest request) {
        try {
            if(userRepository.findUserByEmail(request.getEmail()).isPresent()){
                return BaseResponse.builder()
                        .isError(true)
                        .message("User email already exist!")
                        .build();
            }else{
                var user = User.builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .role(Role.USER)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();

                var savedUser = userRepository.save(user);

                var jwtToken = jwtService.generateToken(user);
                return BaseResponse.builder()
                        .isError(false)
                        .message("Sign Up Success")
                        .build();
            }

        } catch (Exception e){
            throw new UdemyRuntimeException(e.getMessage());
        }

    }

    public AuthenticationRespone authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationRespone.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationRespone login(LoginRequest request) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            var user = userRepository.findUserByEmail(request.getEmail())
                    .orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationRespone.builder()
                    .token(jwtToken)
                    .userId(user.getId())
                    .build();
        } catch (Exception e){
            throw new UdemyRuntimeException(e.getMessage());
        }

    }
}
