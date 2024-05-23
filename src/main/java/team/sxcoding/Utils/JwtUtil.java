package team.sxcoding.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "sxcoding"; // 需要替换为自己的密钥

    public static String generateToken(String id, String subject,Long ttlMillis) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + ttlMillis);

        return Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Jws<Claims> parseToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
    }
}



