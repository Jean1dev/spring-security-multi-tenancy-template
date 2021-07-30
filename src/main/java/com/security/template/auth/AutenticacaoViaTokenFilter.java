package com.security.template.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UsuarioAutenticaService service;

    public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioAutenticaService service) {
        this.tokenService = tokenService;
        this.service = service;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = pegarToken(httpServletRequest);
        if (tokenService.isTokenValido(token)) {
            autenticarCliente(token, httpServletRequest);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void autenticarCliente(String token, HttpServletRequest request) {
        String login = tokenService.getLogin(token);
        UsuarioAutenticado usuarioAutenticado = service.find(login);
        request.setAttribute("tenant", usuarioAutenticado.getTenant());
        request.setAttribute("user_access", usuarioAutenticado.getId());
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuarioAutenticado, null, usuarioAutenticado.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private String pegarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (Objects.isNull(token) || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
