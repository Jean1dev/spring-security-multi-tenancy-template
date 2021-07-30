package com.security.template.domain.usuario.api;

import com.security.template.domain.usuario.api.dto.CriarUsuarioDto;
import com.security.template.domain.usuario.service.CriarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = UsuarioApi.PATH)
public class UsuarioApi {

    public static final String PATH = "usuarios";

    @Autowired
    private CriarUsuarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarUsuario(@RequestBody @Valid CriarUsuarioDto dto) {
        service.criar(dto);
    }
}
