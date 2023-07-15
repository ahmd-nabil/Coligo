package nabil.coligo.services.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import nabil.coligo.exceptions.JwtException;
import nabil.coligo.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

/**
 * @author Ahmed Nabil
 */
@Service
public class JwtService {
    private static final String BASE64_ENCODED_SECRET_KEY = "Uc6DVtBfQTwM1LHQ3gw+88gOoKpkh758sOQypCnGmnE=";
    private static final String ISSUER = "COLIGO";
    public String generateToken(User user) {
        return Jwts
                .builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuer(ISSUER)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 30*60*1000))
                .signWith(getSignKey())
                .compact();
    }

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return generateToken(user);
    }


    public boolean isValid(String token) throws Exception {
        // Parse the JWT and extract the claims
        Claims claims = getClaims(token);

        // Check the expiration time
        Date exp = claims.getExpiration();
        if (exp.before(new Date())) {
            throw new JwtException("JWT has expired");
        }

        // Verify the issuer
        String iss = claims.getIssuer();
        if (!ISSUER.equals(iss)) {
            throw new JwtException("Invalid issuer.");
        }
        return true;
    }

    private Claims getClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }
    public Set<GrantedAuthority> getAuthorities(String token) {
        String role = getClaims(token).get("role", String.class);
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_"+role));
    }
    private Key getSignKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(BASE64_ENCODED_SECRET_KEY));
    }
}
