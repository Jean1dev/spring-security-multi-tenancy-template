package com.security.template.domain.usuario.service;

import com.security.template.context.AuthenticationHolder;
import com.security.template.domain.tenant.model.Tenant;
import com.security.template.domain.tenant.repository.TenantRepository;
import com.security.template.domain.usuario.api.dto.CriarUsuarioDto;
import com.security.template.domain.usuario.model.UsuarioAcesso;
import com.security.template.domain.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CriarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TenantRepository tenantRepository;

    public void criar(CriarUsuarioDto dto) {
        Tenant tenant = criarNovoTenant(dto.getLogin());
        usuarioRepository.save(UsuarioAcesso.builder()
                .login(dto.getLogin())
                .password(new BCryptPasswordEncoder().encode(dto.getPassword()))
                .tenant(tenant)
                .build());
    }

    private Tenant criarNovoTenant(String nomeTenant) {
        return tenantRepository.save(Tenant.builder()
                .nome(nomeTenant)
                .ativo(true)
                .build());
    }
}
