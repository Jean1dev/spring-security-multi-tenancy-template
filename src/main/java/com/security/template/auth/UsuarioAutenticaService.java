package com.security.template.auth;

import com.security.template.domain.usuario.model.UsuarioAcesso;
import com.security.template.domain.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioAutenticaService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioAutenticado find(String login) {
        UsuarioAcesso usuarioAcesso = repository.findByLogin(login).orElseThrow();

        if (!usuarioAcesso.getContaConfirmada())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuario ainda não confirmado");

        return UsuarioAutenticado.builder()
                .id(usuarioAcesso.getId())
                .login(usuarioAcesso.getLogin())
                .password(usuarioAcesso.getPassword())
                .tenant(usuarioAcesso.getTenant().getId())
                .build();
    }
}
