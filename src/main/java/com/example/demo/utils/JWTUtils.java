package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class JWTUtils {
    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(String login) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withClaim("login", login)
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(new Date().toInstant().plusSeconds(86400)))
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .build();

        DecodedJWT jwt = verifier.verify(token);

        return jwt.getClaim("login").asString();
    }
}
