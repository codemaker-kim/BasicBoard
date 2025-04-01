package org.project.basicboard.global.jwt.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.project.basicboard.auth.api.dto.request.UserInfoDto;
import org.project.basicboard.global.jwt.api.dto.TokenDto;
import org.project.basicboard.global.jwt.exception.EmptyClaimsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import static org.project.basicboard.global.jwt.TokenExpiry.ACCESS_TOKEN_EXPIRY_TIME;
import static org.project.basicboard.global.jwt.TokenExpiry.REFRESH_TOKEN_EXPIRY_TIME;
import static org.project.basicboard.user.domain.Role.ROLE_USER;


@Slf4j
@Component
public class TokenProvider {
    private final Key key;

    public TokenProvider(@Value("${jwt.secret}") String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 토큰 입니다.", e);
        } catch (ExpiredJwtException e) {
            log.info("JWT 토큰이 만료되었습니다.", e);
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 비어있습니다.", e);
        }

        return false;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);

        if (claims.get("id") == null)
            throw new EmptyClaimsException();

        Set<SimpleGrantedAuthority> authorities =
                Collections.singleton(new SimpleGrantedAuthority(ROLE_USER.name()));

        UserDetails principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public TokenDto generateToken(UserInfoDto dto) {
        String accessToken = generateAccessToken(dto.userId(), dto.username());
        String refreshToken = generateRefreshToken();

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String createAccessTokenForRefresh(UserInfoDto dto) {
        return generateAccessToken(dto.userId(), dto.username());
    }

    private String generateRefreshToken() {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + REFRESH_TOKEN_EXPIRY_TIME.getExpiry());

        return Jwts.builder()
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private String generateAccessToken(Long userId, String username) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + ACCESS_TOKEN_EXPIRY_TIME.getExpiry());

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .claim("id", userId)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}