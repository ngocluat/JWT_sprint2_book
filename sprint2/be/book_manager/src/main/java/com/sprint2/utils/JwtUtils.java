package com.sprint2.utils;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private String secretKey = "vn.codegym.pharmacy_C1221G1";
    private final int jwtExpirationMs = 8640000;
    private final int jwtRefreshTokenExpirationMs = 12460000;
    private Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    public String generateJwtKey(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512,secretKey)
                .compact();
    }

    public String generateRefreshToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return  Jwts.builder().setSubject(user.getUsername()).
                setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtRefreshTokenExpirationMs))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

    public String getUsernameFromJwtKey(String authToken){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException e){
            logger.error("Invalid JWT Signature: {}",e.getMessage());
        }catch (MalformedJwtException e){
            logger.error("Invalid JWT Token: {}",e.getMessage());
        } catch (ExpiredJwtException e){
            logger.error("JWT token is expired: {}",e.getMessage());
        } catch (UnsupportedJwtException e){
            logger.error("JWT token is unsupported: {}",e.getMessage());
        } catch (IllegalArgumentException e){
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;

    }
}
