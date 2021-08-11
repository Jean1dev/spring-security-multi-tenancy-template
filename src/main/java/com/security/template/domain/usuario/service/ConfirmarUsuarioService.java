package com.security.template.domain.usuario.service;

import com.security.template.domain.usuario.model.UsuarioAcesso;
import com.security.template.domain.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ConfirmarUsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public void confirmar(UsuarioAcesso usuarioAcesso) {
        usuarioAcesso.setContaConfirmada(true);
        repository.save(usuarioAcesso);
    }
}
