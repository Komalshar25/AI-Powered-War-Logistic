package com.warlogistics.controller;

import com.warlogistics.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest req) {
        // placeholder: accept any credentials for now
        return ResponseEntity.ok("token-placeholder");
    }
}
package com.warlogistics.controller;

import com.warlogistics.dto.LoginRequest;
import com.warlogistics.service.AuthService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.nio.file.Files;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String home() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping(value = "/logistics", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> logistics() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/logistics_optimizer_ui.html");
        String content = new String(Files.readAllBytes(resource.getFile().toPath()));
        return ResponseEntity.ok(content);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.authenticate(loginRequest);
        return ResponseEntity.ok(token);
    }
}
