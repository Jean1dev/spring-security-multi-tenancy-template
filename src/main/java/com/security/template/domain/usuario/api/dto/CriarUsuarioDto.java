package com.security.template.domain.usuario.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriarUsuarioDto {

    @NotEmpty(message = "email é obrigatorio")
    private String email;

    @NotEmpty(message = "login é obrigatorio")
    private String login;

    @NotEmpty(message = "senha é obrigatorio")
    private String password;
}
