package com.cineflix.vn.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Base64;
@Component
public class JWTUtil {

    private final String secret = "c9tN1u5MJkUuhqg8ml+CLqYtWsZfGxwjVZX+DVIAIipJvYuLomZGarmDsgt0ykD7";

    private final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 10;

    private final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 7;

    public String generateAccesstoken(UserDetails userDetails) {
        return createToken(userDetails.getUsername(), ACCESS_TOKEN_EXPIRATION, userDetails.getAuthorities());
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return createToken(userDetails.getUsername(), REFRESH_TOKEN_EXPIRATION, userDetails.getAuthorities());
    }

    private String createToken(String subject, long expirationTime, Collection<? extends GrantedAuthority> authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", authorities.stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toList()));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, java.util.Base64.getDecoder().decode(secret))// This line is the issue
                .compact();
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(Base64.getDecoder().decode(secret))
                .parseClaimsJws(token)
                .getBody();
    }
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // ham kiem tra thoi gian cua refesh token

    public Boolean isRefeshTokenEpired(String Refeshtoken) {
        return extractExpiration(Refeshtoken).before(new Date());
    }

//
//    public Boolean validateRefeshToken(String token, UserDetails userDetails) {
//        String username = extractUsername(token);
//        return !isRefeshTokenEpired(token);
//    }
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("roles", List.class);
    }
}
