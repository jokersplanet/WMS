package team.sxcoding.Utils;

import io.jsonwebtoken.*;

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





    public static Claims parseToken(String token) {
        Claims claims ;
        try {
            claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e) {
            claims = e.getClaims();
        }

        return claims;
    }
}



