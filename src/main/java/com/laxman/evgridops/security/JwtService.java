package com.laxman.evgridops.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    @Value("${jwt.expiration}")
//    private long jwtExpiration;
//
//    private SecretKey getSignInKey() {
//        return Keys.hmacShaKeyFor(secretKey.getBytes());
//    }

//    public String generateToken(String username) {
//
//        return Jwts.builder()
//                .subject(username)
//                .issuedAt(new Date())
//                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
}