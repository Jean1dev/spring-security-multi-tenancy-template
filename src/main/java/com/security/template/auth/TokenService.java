package com.security.template.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        UsuarioAutenticado principal = (UsuarioAutenticado) authentication.getPrincipal();
        Date now = new Date();
        Date dataExpircao = new Date(now.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("LIVE DA TWITCH")
                .setSubject(principal.getLogin())
                .setIssuedAt(now)
                .setExpiration(dataExpircao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getLogin(String token) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return body.getSubject();
    }
}
