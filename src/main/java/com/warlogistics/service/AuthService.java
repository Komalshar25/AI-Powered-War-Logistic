package com.warlogistics.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public String authenticate(String username, String password) {
        // placeholder: return a dummy token
        return "token-placeholder";
    }
}
package com.warlogistics.service;

import com.warlogistics.dto.LoginRequest;
import com.warlogistics.model.User;
import com.warlogistics.repository.UserRepository;
import com.warlogistics.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
        if (user.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
            return jwtUtil.generateToken(user.get().getUsername());
        }
        throw new RuntimeException("Invalid credentials");
    }
}
