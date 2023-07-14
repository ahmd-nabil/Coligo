package nabil.coligo.services.auth;

import lombok.RequiredArgsConstructor;
import nabil.coligo.model.Role;
import nabil.coligo.model.User;
import nabil.coligo.model.auth.LoginRequest;
import nabil.coligo.model.auth.RegisterRequest;
import nabil.coligo.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Ahmed Nabil
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public String login(LoginRequest request) {
        // create UserNamePasswordAuthToken with loginRequest
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        // send it to provider Manager to authenticate it
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        // authenticate() will throw exception if not found (never returns null)
        // if so create the required jwt and return it back to the user
        return jwtService.generateToken(authentication);
    }

    public String register(RegisterRequest request) {
        User user =
                User.builder()
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .role(Role.STUDENT)
                        .build();
        userRepository.save(user);
        // create the required jwt and return it back to the user
        String jwt = jwtService.generateToken(user);
        return jwt;
    }
}
