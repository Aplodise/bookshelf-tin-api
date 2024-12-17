package com.roman.bookshelf.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.roman.bookshelf.domain.user.UserDto;
import com.roman.bookshelf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthenticationProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    private final UserService userService;

    public String createToken(UserDto user) {
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + 3_600_000);
        return JWT.create()
                .withSubject(user.getLogin())
                .withClaim("role", user.getRole().name())
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        UserDto userDto = userService.findByLogin(decoded.getSubject());

        return new UsernamePasswordAuthenticationToken(userDto, null, Collections.singletonList(userDto.getRole()));
    }
}
