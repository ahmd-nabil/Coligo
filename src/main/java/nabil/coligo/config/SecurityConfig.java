package nabil.coligo.config;

import lombok.RequiredArgsConstructor;
import nabil.coligo.filters.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Ahmed Nabil
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/api/v1/auth/**").permitAll();
                    registry.requestMatchers(HttpMethod.POST).hasAuthority("ROLE_INSTRUCTOR");
                    registry.requestMatchers(HttpMethod.PUT).hasAuthority("ROLE_INSTRUCTOR");
                    registry.requestMatchers(HttpMethod.PATCH).hasAuthority("ROLE_INSTRUCTOR");
                    registry.requestMatchers(HttpMethod.DELETE).hasAuthority("ROLE_INSTRUCTOR");
                    registry.anyRequest().authenticated();
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
