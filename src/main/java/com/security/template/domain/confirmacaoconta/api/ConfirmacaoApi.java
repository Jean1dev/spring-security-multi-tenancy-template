package com.security.template.domain.confirmacaoconta.api;

import com.security.template.domain.confirmacaoconta.service.ConfirmacaoContaService;
import com.security.template.domain.usuario.service.ConfirmarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = ConfirmacaoApi.PATH)
public class ConfirmacaoApi {

    public static final String PATH = "confirmar";

    @Autowired
    private ConfirmacaoContaService confirmacaoContaService;

    @Autowired
    private ConfirmarUsuarioService confirmarUsuarioService;

    @GetMapping(path = "{codigo}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void confirmarEmail(@PathVariable("codigo") Integer codigo) {
        confirmarUsuarioService.confirmar(confirmacaoContaService.getUsuarioFromCodigo(codigo));
    }
}
