package nabil.coligo.controllers;

import lombok.RequiredArgsConstructor;
import nabil.coligo.model.AuthResponse;
import nabil.coligo.model.auth.LoginRequest;
import nabil.coligo.model.auth.RegisterRequest;
import nabil.coligo.services.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ahmed Nabil
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Validated LoginRequest request) {
        AuthResponse response = getAuthResponse(authService.login(request));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Validated RegisterRequest request) {
        AuthResponse response = getAuthResponse(authService.register(request));
        return ResponseEntity.ok(response);
    }

    private AuthResponse getAuthResponse(String token) {
        return new AuthResponse(token);
    }
}
