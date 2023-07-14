package nabil.coligo.config;

import lombok.RequiredArgsConstructor;
import nabil.coligo.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Ahmed Nabil
 */
@Configuration
@RequiredArgsConstructor
public class AuthConfig {
    private final UserRepository userRepository;

    @Bean
    UserDetailsService userDetailsService() {
        return email -> userRepository.findByEmailIgnoreCase(email);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
