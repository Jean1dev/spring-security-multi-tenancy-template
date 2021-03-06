package com.security.template.domain.confirmacaoconta.service;

import com.security.template.domain.usuario.model.UsuarioAcesso;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Random;

@Service
public class ConfirmacaoContaService {

    private HashMap<Integer, UsuarioAcesso> listaCodigos = new HashMap<>();

    public UsuarioAcesso getUsuarioFromCodigo(Integer codigo) {
        if (!verificarSeOCodigoJaEstaNaLista(codigo))
            throw new ValidationException("Codigo expirou");

        UsuarioAcesso usuarioAcesso = listaCodigos.get(codigo);
        listaCodigos.remove(codigo);
        return usuarioAcesso;
    }

    public Integer gerarCodigo(UsuarioAcesso usuarioAcesso) {
        int codigoAleatorio = novoInteiro();

        while (verificarSeOCodigoJaEstaNaLista(codigoAleatorio)) {
            codigoAleatorio = novoInteiro();
        }

        listaCodigos.put(codigoAleatorio, usuarioAcesso);
        System.out.println("Codigo gerado " + codigoAleatorio);
        return codigoAleatorio;
    }

    private int novoInteiro() {
        return new Random().nextInt(10000);
    }

    private boolean verificarSeOCodigoJaEstaNaLista(int codigoAleatorio) {
        return listaCodigos.containsKey(codigoAleatorio);
    }
}
