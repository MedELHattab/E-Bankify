package org.example.ebankify.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class CustomJwtUtil {

    private final String SECRET_KEY = "jfzbieu  รง7891JKDHIUAZHDIUE8237YR3H2IDX298HDIUP3HD92738DH13รง7891JKDHIUAZHDIUE8237YR3H2IDX298HDIUP3HD92738DH13รง7891JKDHIUAZHDIUE8237YR3H2IDX298HDIUP3HD92738DH13รง7891JKDHIUAZHDIUE8237YR3H2IDX298HDIUP3HD92738DH13";
    private final long EXPIRATION_TIME = 86400000;

    // Generate the JWT token
    public String generateToken(String email) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512);

        return jwtBuilder.compact();
    }

    // Retrieve the signing key
    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(); // Directly get bytes without Base64 decoding
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName());
    }

    // Validate the token by checking email and expiration
    public boolean validateToken(String token, String email) {
        final String extractedEmail = extractEmail(token);
        return (extractedEmail.equals(email) && !isTokenExpired(token));
    }

    // Extract email from the token
    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    // Extract claims from the token
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Check if the token is expired
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}