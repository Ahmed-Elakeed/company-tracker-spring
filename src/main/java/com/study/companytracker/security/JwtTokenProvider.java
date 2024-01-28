package com.study.companytracker.security;

import com.study.companytracker.dto.LoginDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


@Component
public class JwtTokenProvider {


    @Value("${auth.secret}")
    private String SECRET_KEY;

    private static Key hmacKey;

    private final UserDetailsService userDetailsService;

    @PostConstruct
    private void securityInit() {
        hmacKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET_KEY),
                SignatureAlgorithm.HS256.getJcaName());
    }

    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String generateToken(LoginDTO loginDTO) {
        return Jwts.builder()
                .claim("email", loginDTO.getEmail())
                .claim("password", loginDTO.getPassword())
                .setSubject(loginDTO.getEmail())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(1L, ChronoUnit.MINUTES)))
                .signWith(hmacKey)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("authToken");
    }

    public TokenStatus validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(hmacKey).build().parseClaimsJws(token);
            return TokenStatus.VALID;
        }catch (ExpiredJwtException expiredJwtException){
            return TokenStatus.EXPIRED;
        }
        catch (Exception e){
            return TokenStatus.ERROR;
        }
    }

    public Authentication getAuthentication(String token) {
        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(hmacKey).build().parseClaimsJws(token);
        String username = (String) jwt.getBody().get("email");

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails, "", new ArrayList<>());
    }
}

