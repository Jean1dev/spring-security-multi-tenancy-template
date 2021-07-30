package com.security.template.auth;

import com.security.template.domain.usuario.model.UsuarioAcesso;
import com.security.template.domain.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAutenticaService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioAutenticado find(String login) {
        UsuarioAcesso usuarioAcesso = repository.findByLogin(login).orElseThrow();

        return UsuarioAutenticado.builder()
                .id(usuarioAcesso.getId())
                .login(usuarioAcesso.getLogin())
                .password(usuarioAcesso.getPassword())
                .tenant(usuarioAcesso.getTenant().getId())
                .build();
    }
}
