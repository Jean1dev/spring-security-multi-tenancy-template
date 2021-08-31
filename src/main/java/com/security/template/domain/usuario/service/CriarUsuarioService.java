package com.security.template.domain.usuario.service;

import com.security.template.domain.confirmacaoconta.service.ConfirmacaoContaService;
import com.security.template.domain.tenant.model.Tenant;
import com.security.template.domain.tenant.repository.TenantRepository;
import com.security.template.domain.usuario.api.dto.CriarUsuarioDto;
import com.security.template.domain.usuario.model.UsuarioAcesso;
import com.security.template.domain.usuario.repository.UsuarioRepository;
import com.security.template.provider.MailSenderService;
import com.security.template.provider.dto.SendMailDto;
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
    private MailSenderService mailSender;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private ConfirmacaoContaService confirmacaoContaService;

    public void criar(CriarUsuarioDto dto) {
        Tenant tenant = criarNovoTenant(dto.getLogin());
        UsuarioAcesso acesso = usuarioRepository.save(UsuarioAcesso.builder()
                .login(dto.getLogin())
                .password(new BCryptPasswordEncoder().encode(dto.getPassword()))
                .tenant(tenant)
                .contaConfirmada(false)
                .build());

        mailSender.sendEmail(SendMailDto.builder()
                .subject("Verifique sua conta")
                .text(confirmacaoContaService.gerarCodigo(acesso).toString())
                .to(dto.getEmail())
                .build());
    }

    private Tenant criarNovoTenant(String nomeTenant) {
        return tenantRepository.save(Tenant.builder()
                .nome(nomeTenant)
                .ativo(true)
                .build());
    }
}
