package com.security.template.auth.dto;

import com.security.template.auth.UsuarioAutenticado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {

    private String token;
    private Object usuarioAutenticado;
}
