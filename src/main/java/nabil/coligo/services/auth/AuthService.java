package nabil.coligo.services.auth;

import lombok.RequiredArgsConstructor;
import nabil.coligo.model.Role;
import nabil.coligo.model.User;
import nabil.coligo.model.auth.LoginRequest;
import nabil.coligo.model.auth.RegisterRequest;
import nabil.coligo.repositories.UserRepository;
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
    public String login(LoginRequest request) {
        // create UserNamePasswordAuthToken with loginRequest
        // send it to provider Manager to authenticate it
        // return if authenticated or not
        // if so create the required jwt and return it back to the user
        return "";
    }

    public String register(RegisterRequest request) {
        System.out.println();
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
