package nabil.coligo.services.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import nabil.coligo.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

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
                .setExpiration(new Date(System.currentTimeMillis()+24*60*60*1000))
                .signWith(getSignKey())
                .compact();
    }

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return Jwts
                .builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuer(ISSUER)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+24*60*60*1000))
                .signWith(getSignKey())
                .compact();
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(BASE64_ENCODED_SECRET_KEY));
    }
}
