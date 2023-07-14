package nabil.coligo.services.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import nabil.coligo.model.User;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

/**
 * @author Ahmed Nabil
 */
@Service
public class JwtService {
    private static final String secretString = "Uc6DVtBfQTwM1LHQ3gw+88gOoKpkh758sOQypCnGmnE=";

    public String generateToken(User user) {
        return Jwts
                .builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+24*60*60*1000))
                .signWith(getSignKey())
                .compact();
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
    }
}
