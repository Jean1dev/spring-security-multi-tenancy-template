package com.security.template.domain.usuario.repository;

import com.security.template.domain.usuario.model.UsuarioAcesso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioAcesso, Integer> {

    Optional<UsuarioAcesso> findByLogin(String login);
}
